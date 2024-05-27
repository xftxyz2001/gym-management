<template>
  <div class="layout-container">
    <!-- 教练信息 -->
    <div class="layout-container-form flex">
      <el-form ref="form" :model="formModel" label-width="100px" class="centered-form">
        <el-form-item style="margin-top: 20px" label="教练姓名" prop="name">
          <el-input v-model="formModel.name" placeholder="请输入教练姓名"></el-input>
        </el-form-item>
        <el-form-item style="margin-top: 20px" label="教练专长" prop="skill">
          <el-input type="textarea" v-model="formModel.skill" placeholder="请输入教练专长"></el-input>
        </el-form-item>
        <el-form-item style="margin-top: 20px">
          <el-button type="primary" @click="submit">保存</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup>
import { getCoach, updateCoach } from "@/api/course";
import { getCoachLoginInfo } from "@/api/admin";
import { ElMessage } from "element-plus";
import { onBeforeMount, ref } from "vue";
import { useStore } from "vuex";

// 表单数据
const formModel = ref({
  name: "",
  skill: ""
});

const store = useStore();

onBeforeMount(() => {
  getCoachData();
});

// 获取教练数据
function getCoachData() {
  getCoach(store.state.user.info.coachId).then(res => {
    formModel.value = JSON.parse(JSON.stringify(res));
  });
}

// 提交修改
function submit() {
  updateCoach(formModel.value).then(() => {
    ElMessage.success("修改成功");
    getCoachData();

    // 更新右上角的用户信息
    getCoachLoginInfo(store.state.user.info.coachId).then(res => {
      store.commit("user/infoChange", res);
    });
  });
}
</script>

<style scoped>
.centered-form {
  margin: 0 auto;
  width: 1000px;
}
</style>
