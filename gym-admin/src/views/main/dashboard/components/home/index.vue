<template>
  <div class="container">
    <el-button id="btnRegister" class="full-size-button" type="primary" @click="handleRegisterCard">办卡</el-button>
    <el-button id="btnLogin" class="full-size-button" type="primary" @click="handleLoginCard">刷卡</el-button>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { getMemberByContact, getCard, getCardByContact } from "@/api/member";
import { ElButton, ElMessage, ElMessageBox } from "element-plus";

import { useRouter } from "vue-router";
const router = useRouter();

const confirmButtonText = ref("前往添加会员信息");

function goBusinessRegister(id) {
  router.push({ name: "businessRegister", params: { id } });
}

function gotoMemberMember() {
  router.push("/member/member");
}

function goBusinessIndex(id) {
  router.push({ name: "businessIndex", params: { id } });
}

function handleRegisterCard() {
  // 弹出输入框，输入手机号
  ElMessageBox.prompt("请输入手机号", "提示", {
    confirmButtonText: confirmButtonText,
    cancelButtonText: "取消",
    inputValidator: value => {
      if (value) {
        confirmButtonText.value = "确定";
      } else {
        confirmButtonText.value = "前往添加会员信息";
      }
      return true;
    }
  })
    .then(({ value }) => {
      if (confirmButtonText.value === "确定") {
        getMemberByContact(value)
          .then(res => {
            goBusinessRegister(res.id);
          })
          .catch(() => {
            ElMessageBox.confirm("还不是会员，前往添加会员信息？", "提示", {
              confirmButtonText: "确定",
              cancelButtonText: "取消"
            })
              .then(() => {
                gotoMemberMember();
              })
              .catch(() => {
                confirmButtonText.value = "前往添加会员信息";
              });
          });
      } else {
        confirmButtonText.value = "前往添加会员信息";
        gotoMemberMember();
      }
    })
    .catch(() => {
      confirmButtonText.value = "前往添加会员信息";
    });
}

function handleLoginCard() {
  // 弹出输入框，输入卡号/手机号
  ElMessageBox.prompt("请输入卡号/手机号", "提示", {
    confirmButtonText: "确认",
    cancelButtonText: "取消"
  }).then(({ value }) => {
    getCard(value)
      .then(res => {
        goBusinessIndex(res.id);
      })
      .catch(() => {
        getCardByContact(value)
          .then(res => {
            goBusinessIndex(res.id);
          })
          .catch(() => {
            ElMessage.error("卡号/手机号不存在");
          });
      });
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
  height: 80%; /* 设置按钮高度为容器的高度 */
  margin: 20px 50px; /* 设置按钮之间的间距 */
  font-size: 50px;
}
</style>
