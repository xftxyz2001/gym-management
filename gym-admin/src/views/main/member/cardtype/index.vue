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
        <!-- <el-input v-model="query.name" placeholder="类型名称"></el-input>
          <el-input v-model="query.price" placeholder="价格"></el-input>
          <el-button type="primary" :icon="Search" class="search-btn" @click="getTableData">搜索</el-button> -->
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
        <el-table-column prop="name" label="类型名称"></el-table-column>
        <el-table-column prop="price" label="价格（元）"></el-table-column>
        <el-table-column prop="validTime" label="有效期（天）"></el-table-column>
        <el-table-column prop="count" label="次数"></el-table-column>

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
        <el-form-item label="类型名称" prop="name">
          <el-input v-model="formModel.name" placeholder="请输入类型名称"></el-input>
        </el-form-item>
        <el-form-item label="价格（元）" prop="price">
          <el-input v-model="formModel.price" placeholder="请输入价格"></el-input>
        </el-form-item>
        <el-form-item label="有效期（天）" prop="validTime">
          <el-input v-model="formModel.validTime" placeholder="请输入有效期"></el-input>
        </el-form-item>
        <el-form-item label="次数" prop="count">
          <el-input v-model="formModel.count" placeholder="请输入次数"></el-input>
        </el-form-item>
      </el-form>
    </Layer>
  </div>
</template>

<script setup>
import {
  saveCardType,
  removeCardType,
  removeCardTypes,
  updateCardType,
  getCardType,
  listCardTypes
} from "@/api/member";
import Layer from "@/components/layer/index.vue";
import Table from "@/components/table/index.vue";
import { Delete, Plus, Search } from "@element-plus/icons";
import { ElMessage } from "element-plus";
import { onBeforeMount, reactive, ref } from "vue";

// 搜索相关
const query = reactive({
  //   name: "",
  //   price: ""
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
  price: "",
  validTime: "",
  count: -1
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
  listCardTypes(query, page.index, page.size).then(res => {
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
  removeCardTypes(ids).then(() => {
    ElMessage.success("删除成功");
    getTableData();
  });
}

// 删除单条数据
function deleteOne(row) {
  removeCardType(row.id).then(() => {
    ElMessage.success("删除成功");
    getTableData();
  });
}

// 新增/编辑弹窗
function openAddDialog() {
  formModel.value = {
    name: "",
    price: "",
    validTime: "",
    count: -1
  };

  layer.title = "新增记录";
  layer.show = true;
  layer.showButton = true;
}

function openEditDialog(row) {
  getCardType(row.id).then(res => {
    formModel.value = JSON.parse(JSON.stringify(res));
    layer.title = "编辑记录";
    layer.show = true;
    layer.showButton = true;
  });
}

// 提交新增/修改
function submit() {
  if (formModel.value.id) {
    updateCardType(formModel.value).then(() => {
      ElMessage.success("修改成功");
      layer.show = false;
      getTableData();
    });
  } else {
    saveCardType(formModel.value).then(() => {
      ElMessage.success("新增成功");
      layer.show = false;
      getTableData();
    });
  }
}
</script>
