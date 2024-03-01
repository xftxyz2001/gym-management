<template>
  <div class="container">
    <el-button class="full-size-button" type="primary" @click="handleRegisterCard">办卡</el-button>
    <el-button class="full-size-button" type="primary" @click="handleLoginCard">刷卡</el-button>
  </div>
</template>

<script setup>
import { ElButton, ElMessageBox } from "element-plus";
import { useRouter } from "vue-router";
const router = useRouter();

function handleRegisterCard() {
  router.push("/business/register");
}

function handleLoginCard() {
  // 弹出输入框，输入卡号/手机号
  ElMessageBox.prompt("请输入卡号/手机号", "提示", {
    confirmButtonText: "确定",
    cancelButtonText: "取消"
  }).then(({ value }) => {
    // 确认后跳转到刷卡页面
    router.push({ name: "businessIndex", params: { id: value } });
  });
}
</script>

<style scoped>
.container {
  padding: 20px;
  display: flex;
  height: 50vh; /* 设置容器高度为视口的高度 */
}

.full-size-button {
  flex: 1; /* 让按钮占满容器的空间 */
  height: 100%; /* 设置按钮高度为容器的高度 */
  margin: 0 30px; /* 设置按钮之间的间距 */
  font-size: 50px;
}
</style>
