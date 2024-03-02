<template>
  <div class="layout-container">
    <div style="width: 800px">
      <!-- 会员卡信息 -->
      <el-card>
        <el-form :model="card" label-width="100px" disabled>
          <el-form-item label="卡类型" prop="cardType">
            <el-input v-model="cardType.name" placeholder="请输入卡类型"></el-input>
          </el-form-item>
          <!-- <el-form-item label="价格（元）" prop="price">
            <el-input v-model="cardType.price" placeholder="请输入价格"></el-input>
          </el-form-item> -->
          <el-form-item label="有效期（天）" prop="validTime">
            <el-input v-model="card.validTime" placeholder="请输入有效期"></el-input>
          </el-form-item>
          <el-form-item label="剩余次数" prop="remain">
            <el-input v-model="card.remain" placeholder="请输入次数"></el-input>
          </el-form-item>
        </el-form>
      </el-card>

      <!-- 会员信息 -->
      <el-card>
        <el-form :model="member" label-width="100px" disabled>
          <el-form-item label="会员姓名" prop="name">
            <el-input v-model="member.name" placeholder="请输入会员姓名"></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="gender">
            <el-radio-group v-model="member.gender">
              <el-radio :label="0">女</el-radio>
              <el-radio :label="1">男</el-radio>
              <el-radio :label="-1">保密</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="出生日期" prop="birthday">
            <el-date-picker
              v-model="member.birthday"
              type="date"
              placeholder="选择日期"
              value-format="YYYY-MM-DD"
            ></el-date-picker>
          </el-form-item>
          <el-form-item label="身高" prop="height">
            <el-input v-model="member.height" placeholder="请输入身高"></el-input>
          </el-form-item>
          <el-form-item label="体重" prop="weight">
            <el-input v-model="member.weight" placeholder="请输入体重"></el-input>
          </el-form-item>
          <el-form-item label="体型" prop="bodyType">
            <el-input v-model="member.bodyType" placeholder="请输入体型"></el-input>
          </el-form-item>
          <el-form-item label="联系方式" prop="contact">
            <el-input v-model="member.contact" placeholder="请输入联系方式"></el-input>
          </el-form-item>
          <el-form-item label="地址" prop="address">
            <el-input v-model="member.address" placeholder="请输入地址"></el-input>
          </el-form-item>
          <el-form-item label="积分" prop="points">
            <el-input v-model="member.points" placeholder="请输入积分"></el-input>
          </el-form-item>
        </el-form>
      </el-card>

      <!-- 操作按钮 -->
      <div style="margin-top: 20px">
        <el-button type="primary" @click="buyCourse">购买课程</el-button>
        <el-button type="primary" @click="exchange">积分兑换</el-button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { getMember, getCard, getCardType } from "@/api/member";
import { ref } from "vue";

import { useRoute, useRouter } from "vue-router";
const route = useRoute();
const router = useRouter();
const { id } = route.params;

const card = ref({});
const member = ref({});
const cardType = ref({});
if (id) {
  getCard(id)
    .then(res => {
      card.value = res;
      getMember(res.memberId).then(res => {
        member.value = res;
      });
      getCardType(res.cardType).then(res => {
        cardType.value = res;
      });
    })
    .catch(() => {
      router.push("/");
    });
} else {
  router.push("/");
}
</script>
