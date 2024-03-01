<template>
  <div class="layout-container">
    <div class="layout-container-form flex space-between">
      <div class="layout-container-form-handle">
        <el-select v-model="selectModule" placeholder="请选择统计模块" @change="somethingChange">
          <el-option v-for="t in chartModules" :key="t.value" :label="t.label" :value="t.value"></el-option>
        </el-select>
      </div>
      <div class="layout-container-form-search">
        <el-select v-model="queryForm.unit" @change="dateUnitChange">
          <el-option v-for="t in datePickerTypes" :key="t.value" :label="t.label" :value="t.value"></el-option>
        </el-select>
        <!-- https://day.js.org/docs/en/display/format#list-of-all-available-formats -->
        <el-date-picker
          v-model="queryForm.from"
          :type="queryForm.unit"
          :disabled-date="disabledDate"
          @change="somethingChange"
          placeholder="开始时间"
          value-format="YYYY-MM-DD"
        ></el-date-picker>
        <el-date-picker
          v-model="queryForm.to"
          :type="queryForm.unit"
          :disabled-date="disabledDate"
          @change="somethingChange"
          placeholder="结束时间"
          value-format="YYYY-MM-DD"
        ></el-date-picker>
        <el-button type="primary" @click="handleSearch">查询</el-button>
      </div>
    </div>

    <div class="layout-container-table">
      <Chart :option="activeOption" />
    </div>
  </div>
</template>

<script setup>
import { memberStatistics, cardStatistics, courseStatistics, incomeStatistics } from "@/api/statistics";
import { ref, reactive, onMounted } from "vue";
import { ElMessage } from "element-plus";
import Chart from "@/components/charts/index.vue";

const chartModules = [
  { label: "注册会员", value: "member" },
  { label: "累计办卡", value: "card" },
  { label: "课程数目", value: "course" },
  { label: "账单总额", value: "income" }
];
const selectModule = ref("");

const datePickerTypes = [
  { label: "年", value: "year" },
  { label: "月", value: "month" },
  { label: "日", value: "date" }
  // { label: "周", value: "week" }
];

const queryForm = reactive({
  unit: "date",
  from: "",
  to: ""
});

// 禁用未来时间
function disabledDate(time) {
  return time.getTime() > Date.now();
}

// 日期单位改变，忽略小的单位
function dateUnitChange(unit) {
  const fromDate = new Date(queryForm.from);
  const toDate = new Date(queryForm.to);
  if (unit === "year") {
    queryForm.from = fromDate.getFullYear() + "-01-01";
    queryForm.to = toDate.getFullYear() + "-01-01";
  } else if (unit === "month") {
    let fromMonth = fromDate.getMonth() + 1;
    let toMonth = toDate.getMonth() + 1;
    fromMonth = fromMonth < 10 ? "0" + fromMonth : fromMonth;
    toMonth = toMonth < 10 ? "0" + toMonth : toMonth;
    queryForm.from = fromDate.getFullYear() + "-" + fromMonth + "-01";
    queryForm.to = toDate.getFullYear() + "-" + toMonth + "-01";
  } else if (unit === "date") {
    queryForm.from = fromDate.toISOString().split("T")[0];
    queryForm.to = toDate.toISOString().split("T")[0];
  }
}

function check(warning = false) {
  if (!selectModule.value) {
    if (warning)
      ElMessage({
        type: "warning",
        message: "请选择统计模块"
      });
    return false;
  }
  if (!queryForm.from) {
    if (warning)
      ElMessage({
        type: "warning",
        message: "请选择开始时间"
      });
    return false;
  }
  if (!queryForm.to) {
    if (warning)
      ElMessage({
        type: "warning",
        message: "请选择结束时间"
      });
    return false;
  }
  // 转化为Date
  const from = new Date(queryForm.from);
  const to = new Date(queryForm.to);
  // 判断时间范围
  if (from > to) {
    if (warning)
      ElMessage({
        type: "warning",
        message: "开始时间需在结束时间之前"
      });
    return false;
  }

  return true;
}

const activeOption = ref({});

// 生成请求参数
function generateParams() {
  const params = [];
  let currentDate = new Date(queryForm.from);
  const endDate = new Date(queryForm.to);

  while (currentDate <= endDate) {
    const from = currentDate.toISOString().split("T")[0];
    if (queryForm.unit === "date") {
      currentDate.setDate(currentDate.getDate() + 1);
    } else if (queryForm.unit === "month") {
      currentDate.setMonth(currentDate.getMonth() + 1);
    } else if (queryForm.unit === "year") {
      currentDate.setFullYear(currentDate.getFullYear() + 1);
    }
    const to = currentDate.toISOString().split("T")[0];
    params.push({ from, to });
  }

  return params;
}

function handleMemberStatistics() {
  // 生成请求参数
  const params = generateParams();
  // 发送请求
  const promises = params.map(param => memberStatistics(param));
  // 处理结果
  Promise.all(promises).then(results => {
    const xAxisData = [];
    const seriesData = [];
    for (const res of results) {
      xAxisData.push(res.from);
      seriesData.push(res.count);
    }
    activeOption.value = {
      title: {
        text: "会员统计"
      },
      tooltip: {},
      legend: {
        data: ["新增会员数量"]
      },
      xAxis: {
        data: xAxisData
      },
      yAxis: {},
      series: [
        {
          name: "新增会员数量",
          type: "bar",
          data: seriesData
        }
      ]
    };
  });
}

function handleCardStatistics() {
  // 生成请求参数
  const params = generateParams();
  // 发送请求
  const promises = params.map(param => cardStatistics(param));
  // 处理结果
  Promise.all(promises).then(results => {
    const xAxisData = [];
    const seriesData = [];
    for (const res of results) {
      xAxisData.push(res.from);
      seriesData.push(res.count);
    }
    activeOption.value = {
      title: {
        text: "办卡统计"
      },
      tooltip: {},
      legend: {
        data: ["办卡数量"]
      },
      xAxis: {
        data: xAxisData
      },
      yAxis: {},
      series: [
        {
          name: "办卡数量",
          type: "bar",
          data: seriesData
        }
      ]
    };
  });
}

function handleCourseStatistics() {
  // 生成请求参数
  const params = generateParams();
  // 发送请求
  const promises = params.map(param => courseStatistics(param));
  // 处理结果
  Promise.all(promises).then(results => {
    const xAxisData = [];
    const seriesData = [];
    for (const res of results) {
      xAxisData.push(res.from);
      seriesData.push(res.count);
    }
    activeOption.value = {
      title: {
        text: "课程统计"
      },
      tooltip: {},
      legend: {
        data: ["课程数量"]
      },
      xAxis: {
        data: xAxisData
      },
      yAxis: {},
      series: [
        {
          name: "课程数量",
          type: "bar",
          data: seriesData
        }
      ]
    };
  });
}

function handleIncomeStatistics() {
  // 生成请求参数
  const params = generateParams();
  // 发送请求
  const promises = params.map(param => incomeStatistics(param));
  // 处理结果
  Promise.all(promises).then(results => {
    const xAxisData = [];
    const seriesData = [];
    for (const res of results) {
      xAxisData.push(res.from);
      seriesData.push(res.count);
    }
    activeOption.value = {
      title: {
        text: "账单统计"
      },
      tooltip: {},
      legend: {
        data: ["账单总额"]
      },
      xAxis: {
        data: xAxisData
      },
      yAxis: {},
      series: [
        {
          name: "账单总额",
          type: "bar",
          data: seriesData
        }
      ]
    };
  });
}

function handleSearch(checked = false) {
  if (!checked) {
    if (!check(true)) {
      return;
    }
  }

  switch (selectModule.value) {
    case "member":
      handleMemberStatistics();
      break;
    case "card":
      handleCardStatistics();
      break;
    case "course":
      handleCourseStatistics();
      break;
    case "income":
      handleIncomeStatistics();
      break;
  }
}

function somethingChange() {
  if (!check()) {
    return;
  }
  handleSearch(true);
}
</script>
