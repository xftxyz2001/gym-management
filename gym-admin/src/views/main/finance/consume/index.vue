<template>
  <div class="layout-container">
    <!-- 头部 -->
    <div class="layout-container-form flex space-between">
      <div class="layout-container-form-handle">
        <el-button type="primary" :icon="Plus" @click="openAddDialog">添加</el-button>
        <el-popconfirm
          title="确定删除选中的数据吗？"
          @confirm="deleteChoose"
          confirm-button-text="确定"
          cancel-button-text="取消"
        >
          <template #reference>
            <el-button type="danger" :icon="Delete" :disabled="chooseData.length === 0">删除选中</el-button>
          </template>
        </el-popconfirm>
      </div>
      <div class="layout-container-form-search">
        <el-input v-model="query.contact" placeholder="会员联系方式"></el-input>
        <el-input v-model="query.status" placeholder="消费状态"></el-input>
        <el-button type="primary" :icon="Search" class="search-btn" @click="getTableData">搜索</el-button>
      </div>
    </div>

    <!-- 表格 -->
    <div class="layout-container-table">
      <Table
        ref="table"
        v-model:page="page"
        v-loading="loading"
        row-key="id"
        :showSelection="true"
        :data="tableData"
        @getTableData="getTableData"
        @selection-change="handleSelectionChange"
      >
        <el-table-column prop="memberId" label="会员ID"></el-table-column>
        <el-table-column prop="ctype" label="消费类型">
          <template v-slot="scope">
            {{ scope.row.ctype === 0 ? "办卡" : "课程" }}
          </template>
        </el-table-column>
        <el-table-column prop="citem" label="消费项目ID"></el-table-column>
        <el-table-column prop="payType" label="支付方式">
          <template v-slot="scope">
            {{
              scope.row.payType === 0
                ? "现金"
                : scope.row.payType === 1
                  ? "刷卡"
                  : scope.row.payType === 2
                    ? "支付宝"
                    : scope.row.payType === 3
                      ? "微信"
                      : "其他"
            }}
          </template>
        </el-table-column>
        <el-table-column prop="amount" label="消费金额"></el-table-column>
        <el-table-column prop="status" label="消费状态">
          <template v-slot="scope">
            <!-- {{ scope.row.status === 0 ? "已支付" : scope.row.status === 1 ? "转入退款" : "已完成" }} -->
            <el-tag v-if="scope.row.status === 0" type="success">已支付</el-tag>
            <el-tag v-else-if="scope.row.status === 1" type="danger">转入退款</el-tag>
            <el-tag v-else-if="scope.row.status === 2" type="info">已完成</el-tag>
          </template>
        </el-table-column>

        <el-table-column label="创建时间" prop="createTime" :show-overflow-tooltip="true"></el-table-column>
        <el-table-column label="更新时间" prop="updateTime" :show-overflow-tooltip="true"></el-table-column>

        <el-table-column label="操作">
          <template v-slot="scope">
            <el-button type="primary" @click="openEditDialog(scope.row)">修改</el-button>
            <el-popconfirm
              title="确定删除吗？"
              @confirm="deleteOne(scope.row)"
              confirm-button-text="确定"
              cancel-button-text="取消"
            >
              <template #reference>
                <el-button type="danger">删除</el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </Table>
    </div>

    <!-- 弹窗 -->
    <Layer :layer="layer" @confirm="submit">
      <el-form ref="form" :model="formModel" label-width="100px">
        <el-form-item label="会员ID" prop="memberId">
          <el-input v-model="formModel.memberId"></el-input>
        </el-form-item>
        <el-form-item label="消费类型" prop="ctype">
          <el-radio-group v-model="formModel.ctype">
            <el-radio :label="0">办卡</el-radio>
            <el-radio :label="1">课程</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="消费项目ID" prop="citem">
          <el-input v-model="formModel.citem"></el-input>
        </el-form-item>
        <el-form-item label="支付方式" prop="payType">
          <el-radio-group v-model="formModel.payType">
            <el-radio :label="0">现金</el-radio>
            <el-radio :label="1">刷卡</el-radio>
            <el-radio :label="2">支付宝</el-radio>
            <el-radio :label="3">微信</el-radio>
            <el-radio :label="-1">其他</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="消费金额" prop="amount">
          <el-input v-model="formModel.amount"></el-input>
        </el-form-item>
        <el-form-item label="消费状态" prop="status">
          <el-radio-group v-model="formModel.status">
            <el-radio :label="0">已支付</el-radio>
            <el-radio :label="1">转入退款</el-radio>
            <el-radio :label="2">已完成</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
    </Layer>
  </div>
</template>

<script setup>
import { saveConsume, removeConsume, removeConsumes, updateConsume, getConsume, listConsumes } from "@/api/finance";
import Layer from "@/components/layer/index.vue";
import Table from "@/components/table/index.vue";
import { Delete, Plus, Search } from "@element-plus/icons";
import { ElMessage } from "element-plus";
import { onBeforeMount, reactive, ref } from "vue";

// 搜索相关
const query = reactive({
  contact: "",
  status: ""
});

// 弹窗控制器
const layer = reactive({
  show: false,
  title: "新增",
  showButton: true
});

// 弹窗表单数据
const formModel = ref({
  memberId: "",
  ctype: 0,
  citem: "",
  payType: 0,
  amount: "",
  status: 0
});

// 分页参数, 供table使用
const page = reactive({
  index: 1,
  size: 20,
  total: 0
});

const loading = ref(true);
const tableData = ref([]);
const chooseData = ref([]);
const handleSelectionChange = val => {
  chooseData.value = val;
};

// 获取表格数据
function getTableData() {
  listConsumes(query, page.index, page.size).then(res => {
    tableData.value = res.records;
    // page.index = res.current
    // page.size = res.size
    page.total = res.total;
    loading.value = false;
  });
}

onBeforeMount(() => {
  getTableData();
});

// 删除选中数据
function deleteChoose() {
  const ids = chooseData.value.map(item => item.id);
  removeConsumes(ids).then(() => {
    ElMessage.success("删除成功");
    getTableData();
  });
}

// 删除单条数据
function deleteOne(row) {
  removeConsume(row.id).then(() => {
    ElMessage.success("删除成功");
    getTableData();
  });
}

// 新增/编辑弹窗
function openAddDialog() {
  formModel.value = {
    memberId: "",
    ctype: 0,
    citem: "",
    payType: 0,
    amount: "",
    status: 0
  };

  layer.title = "新增记录";
  layer.show = true;
  layer.showButton = true;
}

function openEditDialog(row) {
  getConsume(row.id).then(res => {
    formModel.value = JSON.parse(JSON.stringify(res));
    layer.title = "编辑记录";
    layer.show = true;
    layer.showButton = true;
  });
}

// 提交新增/修改
function submit() {
  if (formModel.value.id) {
    updateConsume(formModel.value).then(() => {
      ElMessage.success("修改成功");
      layer.show = false;
      getTableData();
    });
  } else {
    saveConsume(formModel.value).then(() => {
      ElMessage.success("新增成功");
      layer.show = false;
      getTableData();
    });
  }
}
</script>
