<template>
  <div class="layout-container" style="padding: 50px">
    <div style="width: 800px">
      <!-- 会员信息 -->
      <el-form :model="member" label-width="100px" disabled v-show="member.id">
        <el-form-item label="会员姓名" prop="name">
          <el-input v-model="member.name" placeholder="请输入会员姓名"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" prop="contact">
          <el-input v-model="member.contact" placeholder="请输入联系方式"></el-input>
        </el-form-item>
      </el-form>

      <!-- 选择卡种类 -->
      <el-form :model="cardType" label-width="100px">
        <el-form-item label="卡种类*" prop="cardType">
          <el-autocomplete
            v-model="queryString"
            value-key="name"
            :fetch-suggestions="listCardTypesByName"
            @select="handleCardTypeSelect"
            placeholder="输入卡类型名称"
            style="width: 100%"
          ></el-autocomplete>
        </el-form-item>
      </el-form>

      <el-card v-if="selectedCardType.id">
        <el-form ref="form" :model="selectedCardType" label-width="100px" disabled>
          <el-form-item label="类型名称" prop="name">
            <el-input v-model="selectedCardType.name" placeholder="请输入类型名称"></el-input>
          </el-form-item>
          <el-form-item label="价格（元）" prop="price">
            <el-input v-model="selectedCardType.price" placeholder="请输入价格"></el-input>
          </el-form-item>
          <el-form-item label="有效期（天）" prop="validTime">
            <el-input v-model="selectedCardType.validTime" placeholder="请输入有效期"></el-input>
          </el-form-item>
          <el-form-item label="次数" prop="count">
            <el-input v-model="selectedCardType.count" placeholder="请输入次数"></el-input>
          </el-form-item>
        </el-form>
      </el-card>

      <!-- 操作按钮 -->
      <div style="margin-top: 20px">
        <el-button type="primary" @click="submit(0)">现金支付</el-button>
        <el-button type="primary" @click="submit(1)">刷卡支付</el-button>
        <el-button type="primary" @click="submit(2)">支付宝支付</el-button>
        <el-button type="primary" @click="submit(3)">微信支付</el-button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { getMember, register, listCardTypesByName } from "@/api/member";
import { ElMessage, ElMessageBox } from "element-plus";
import { ref } from "vue";

import { useRoute, useRouter } from "vue-router";
const route = useRoute();
const router = useRouter();
const { id } = route.params;

const member = ref({});
if (id) {
  getMember(id)
    .then(res => {
      member.value = res;
    })
    .catch(() => {
      router.push("/");
    });
} else {
  router.push("/");
}

const cardType = ref("");
const queryString = ref("");
const selectedCardType = ref({});
function handleCardTypeSelect(item) {
  selectedCardType.value = item;
}

// 提交办卡信息
function submit(payType) {
  register({
    memberId: member.value.id,
    cardTypeId: selectedCardType.value.id,
    payType
  }).then(() => {
    ElMessage.success("会员卡办理成功");
    router.push("/member/card");
  });
}
</script>
