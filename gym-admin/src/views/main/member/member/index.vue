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
        <el-input v-model="query.name" placeholder="会员姓名"></el-input>
        <el-input v-model="query.contact" placeholder="联系方式"></el-input>
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
        <el-table-column prop="name" label="会员姓名"></el-table-column>
        <el-table-column prop="gender" label="性别" :formatter="genderFormatter"></el-table-column>
        <el-table-column prop="birthday" label="出生日期"></el-table-column>
        <el-table-column prop="height" label="身高"></el-table-column>
        <el-table-column prop="weight" label="体重"></el-table-column>
        <el-table-column prop="bodyType" label="体型"></el-table-column>
        <el-table-column prop="contact" label="联系方式"></el-table-column>
        <el-table-column prop="address" label="地址"></el-table-column>
        <el-table-column prop="points" label="积分"></el-table-column>

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
        <el-form-item label="会员姓名" prop="name">
          <el-input v-model="formModel.name" placeholder="请输入会员姓名"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-radio-group v-model="formModel.gender">
            <el-radio :label="0">女</el-radio>
            <el-radio :label="1">男</el-radio>
            <el-radio :label="-1">保密</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="出生日期" prop="birthday">
          <el-date-picker
            v-model="formModel.birthday"
            type="date"
            placeholder="选择日期"
            value-format="YYYY-MM-DD"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="身高" prop="height">
          <el-input v-model="formModel.height" placeholder="请输入身高"></el-input>
        </el-form-item>
        <el-form-item label="体重" prop="weight">
          <el-input v-model="formModel.weight" placeholder="请输入体重"></el-input>
        </el-form-item>
        <el-form-item label="体型" prop="bodyType">
          <el-input v-model="formModel.bodyType" placeholder="请输入体型"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" prop="contact">
          <el-input v-model="formModel.contact" placeholder="请输入联系方式"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="formModel.address" placeholder="请输入地址"></el-input>
        </el-form-item>
        <el-form-item label="积分" prop="points">
          <el-input v-model="formModel.points" placeholder="请输入积分"></el-input>
        </el-form-item>
      </el-form>
    </Layer>
  </div>
</template>

<script setup>
import { saveMember, removeMember, removeMembers, updateMember, getMember, listMembers } from "@/api/member";
import Layer from "@/components/layer/index.vue";
import Table from "@/components/table/index.vue";
import { Delete, Plus, Search } from "@element-plus/icons";
import { ElMessage } from "element-plus";
import { onBeforeMount, reactive, ref } from "vue";

// 搜索相关
const query = reactive({
  name: "",
  contact: ""
});

// 弹窗控制器
const layer = reactive({
  show: false,
  title: "新增",
  showButton: true
});

// 弹窗表单数据
const formModel = ref({
  name: "",
  gender: -1,
  birthday: "",
  height: "",
  weight: "",
  bodyType: "",
  contact: "",
  address: "",
  points: 0
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
  listMembers(query, page.index, page.size).then(res => {
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

// 性别格式化
function genderFormatter(row) {
  switch (row.gender) {
    case 0:
      return "女";
    case 1:
      return "男";
    case -1:
    default:
      return "保密";
  }
}

// 删除选中数据
function deleteChoose() {
  const ids = chooseData.value.map(item => item.id);
  removeMembers(ids).then(() => {
    ElMessage.success("删除成功");
    getTableData();
  });
}

// 删除单条数据
function deleteOne(row) {
  removeMember(row.id).then(() => {
    ElMessage.success("删除成功");
    getTableData();
  });
}

// 新增/编辑弹窗
function openAddDialog() {
  formModel.value = {
    name: "",
    gender: -1,
    birthday: "",
    height: "",
    weight: "",
    bodyType: "",
    contact: "",
    address: "",
    points: 0
  };

  layer.title = "新增记录";
  layer.show = true;
  layer.showButton = true;
}

function openEditDialog(row) {
  getMember(row.id).then(res => {
    formModel.value = JSON.parse(JSON.stringify(res));
    layer.title = "编辑记录";
    layer.show = true;
    layer.showButton = true;
  });
}

// 提交新增/修改
function submit() {
  if (formModel.value.id) {
    updateMember(formModel.value).then(() => {
      ElMessage.success("修改成功");
      layer.show = false;
      getTableData();
    });
  } else {
    saveMember(formModel.value).then(() => {
      ElMessage.success("新增成功");
      layer.show = false;
      getTableData();
    });
  }
}
</script>
