<template>
  <div class="layout-container">
    <!-- 头部 -->
    <div class="layout-container-form flex space-between">
      <div class="layout-container-form-handle">
        <el-tooltip content="⚠请前往首页>办卡">
          <el-button type="primary" :icon="Plus" @click="openAddDialog">添加</el-button>
        </el-tooltip>
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
        <el-table-column prop="memberId" label="所属会员">
          <template v-slot="scope">{{ scope.row.member.name }}({{ scope.row.member.contact }})</template>
        </el-table-column>
        <el-table-column prop="cardType" label="卡类型">
          <template v-slot="scope">{{ scope.row.cardTypeEntity.name }}</template>
        </el-table-column>

        <el-table-column prop="validTime" label="有效期（至）"></el-table-column>
        <el-table-column prop="total" label="总次数/金额"></el-table-column>
        <el-table-column prop="remain" label="剩余次数/金额"></el-table-column>
        <el-table-column prop="status" label="状态">
          <template v-slot="scope">
            <el-tag v-if="scope.row.status === 0" type="success">正常</el-tag>
            <el-tag v-else-if="scope.row.status === 1" type="danger">过期</el-tag>
            <el-tag v-else-if="scope.row.status === -1" type="info">注销</el-tag>
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
        <el-form-item label="所属会员" prop="memberId">
          <el-input
            :value="`${formModel.member.name}(${formModel.member.contact})`"
            placeholder="请输入所属会员"
            readonly
          ></el-input>
        </el-form-item>
        <el-form-item label="卡类型" prop="cardType">
          <el-autocomplete
            v-model="formModel.cardTypeEntity.name"
            placeholder="请输入卡类型"
            :fetch-suggestions="querySearch"
            @select="handleSelect"
          ></el-autocomplete>
        </el-form-item>
        <el-form-item label="有效期（至）" prop="validTime">
          <el-date-picker
            v-model="formModel.validTime"
            type="date"
            placeholder="选择日期"
            value-format="YYYY-MM-DD"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="总次数/金额" prop="total">
          <el-input v-model="formModel.total" placeholder="请输入总次数/金额"></el-input>
        </el-form-item>
        <el-form-item label="剩余次数/金额" prop="remain">
          <el-input v-model="formModel.remain" placeholder="请输入剩余次数/金额"></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="formModel.status">
            <el-radio :label="0">正常</el-radio>
            <el-radio :label="1">过期</el-radio>
            <el-radio :label="-1">注销</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
    </Layer>
  </div>
</template>

<script setup>
import { saveCard, removeCard, removeCards, updateCard, getCard, listCards, listCardTypesByName } from "@/api/member";
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
  memberId: "",
  member: {},
  cardType: "",
  cardTypeEntity: {},
  validTime: "",
  total: "",
  remain: "",
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
  listCards(query, page.index, page.size).then(res => {
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
  removeCards(ids).then(() => {
    ElMessage.success("删除成功");
    getTableData();
  });
}

// 删除单条数据
function deleteOne(row) {
  removeCard(row.id).then(() => {
    ElMessage.success("删除成功");
    getTableData();
  });
}

function querySearch(queryStr, cb) {
  listCardTypesByName(queryStr).then(res => {
    cb(res.map(item => ({ value: item.name, id: item.id })));
  });
}

function handleSelect(item) {
  formModel.value.cardType = item.id;
}

// 新增/编辑弹窗
function openAddDialog() {
  formModel.value = {
    memberId: "",
    member: {},
    cardType: "",
    cardTypeEntity: {},
    validTime: "",
    total: "",
    remain: "",
    status: 0
  };

  layer.title = "新增记录";
  layer.show = true;
  layer.showButton = true;
}

function openEditDialog(row) {
  getCard(row.id).then(res => {
    formModel.value = JSON.parse(JSON.stringify(res));
    layer.title = "编辑记录";
    layer.show = true;
    layer.showButton = true;
  });
}

// 提交新增/修改
function submit() {
  if (formModel.value.id) {
    updateCard(formModel.value).then(() => {
      ElMessage.success("修改成功");
      layer.show = false;
      getTableData();
    });
  } else {
    saveCard(formModel.value).then(() => {
      ElMessage.success("新增成功");
      layer.show = false;
      getTableData();
    });
  }
}
</script>
