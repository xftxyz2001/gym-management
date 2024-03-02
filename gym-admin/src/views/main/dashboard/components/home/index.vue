<template>
  <div class="container">
    <el-button class="full-size-button" type="primary" @click="handleRegisterCard">办卡</el-button>
    <el-button class="full-size-button" type="primary" @click="handleLoginCard">刷卡</el-button>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { ElButton, ElMessageBox } from "element-plus";

import { useRouter } from "vue-router";
const router = useRouter();

const cbt1 = ref("非会员进入");
const cbt2 = ref("无卡进入");

function handleRegisterCard() {
  // 弹出输入框，输入手机号
  ElMessageBox.prompt("请输入手机号", "提示", {
    confirmButtonText: cbt1,
    cancelButtonText: "取消",
    inputValidator: value => {
      if (value) {
        cbt1.value = "确定";
      } else {
        cbt1.value = "非会员进入";
      }
      return true;
    }
  })
    .then(({ value }) => {
      router.push({ name: "businessRegister", params: { contact: value } });
    })
    .catch(() => {
      cbt1.value = "非会员进入";
    });
}

function handleLoginCard() {
  // 弹出输入框，输入卡号/手机号
  ElMessageBox.prompt("请输入卡号/手机号", "提示", {
    confirmButtonText: cbt2,
    cancelButtonText: "取消",
    inputValidator: value => {
      if (value) {
        cbt2.value = "确定";
      } else {
        cbt2.value = "无卡进入";
      }
      return true;
    }
  })
    .then(({ value }) => {
      router.push({ name: "businessIndex", params: { id: value } });
    })
    .catch(() => {
      cbt2.value = "无卡进入";
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
