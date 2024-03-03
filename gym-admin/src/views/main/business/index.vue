<template>
  <div class="layout-container">
    <div style="width: 800px">
      <!-- 会员卡信息 -->
      <el-card>
        <el-form :model="card" label-width="100px" disabled>
          <el-form-item label="卡类型" prop="cardType">
            <el-input v-model="card.cardType" placeholder="请输入卡类型"></el-input>
          </el-form-item>
          <!-- <el-form-item label="价格（元）" prop="price">
            <el-input v-model="cardType.price" placeholder="请输入价格"></el-input>
          </el-form-item> -->
          <el-form-item label="有效期" prop="validTime">
            <el-input v-model="card.validTime" placeholder="请输入有效期"></el-input>
          </el-form-item>
          <el-form-item label="剩余次数" prop="remain">
            <el-input v-model="card.remain" placeholder="请输入次数"></el-input>
          </el-form-item>
        </el-form>
      </el-card>

      <!-- 会员信息 -->
      <el-button type="info" style="margin: 20px" @click="memberLayer.show = true">查看会员信息</el-button>
      <Layer :layer="memberLayer">
        <el-form :model="card" label-width="100px" disabled>
          <el-form-item label="会员姓名" prop="name">
            <el-input v-model="card.name" placeholder="请输入会员姓名"></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="gender">
            <el-radio-group v-model="card.gender">
              <el-radio :label="0">女</el-radio>
              <el-radio :label="1">男</el-radio>
              <el-radio :label="-1">保密</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="出生日期" prop="birthday">
            <el-date-picker
              v-model="card.birthday"
              type="date"
              placeholder="选择日期"
              value-format="YYYY-MM-DD"
            ></el-date-picker>
          </el-form-item>
          <el-form-item label="身高" prop="height">
            <el-input v-model="card.height" placeholder="请输入身高"></el-input>
          </el-form-item>
          <el-form-item label="体重" prop="weight">
            <el-input v-model="card.weight" placeholder="请输入体重"></el-input>
          </el-form-item>
          <el-form-item label="体型" prop="bodyType">
            <el-input v-model="card.bodyType" placeholder="请输入体型"></el-input>
          </el-form-item>
          <el-form-item label="联系方式" prop="contact">
            <el-input v-model="card.contact" placeholder="请输入联系方式"></el-input>
          </el-form-item>
          <el-form-item label="地址" prop="address">
            <el-input v-model="card.address" placeholder="请输入地址"></el-input>
          </el-form-item>
          <el-form-item label="积分" prop="points">
            <el-input v-model="card.points" placeholder="请输入积分"></el-input>
          </el-form-item>
        </el-form>
      </Layer>

      <!-- 操作按钮 -->
      <div style="margin-top: 20px">
        <el-button
          type="primary"
          style="margin: 0 20px; width: 200px; height: 50px"
          @click="buyCourseLayer.show = true"
        >
          购买课程
        </el-button>
        <el-button type="primary" style="margin: 0 20px; width: 200px; height: 50px" @click="exchangeLayer.show = true">
          积分兑换
        </el-button>
      </div>
    </div>

    <Layer :layer="buyCourseLayer" @confirm="submitBuy">
      <el-form :model="course" label-width="100px">
        <el-form-item label="课程*" prop="course">
          <el-autocomplete
            v-model="queryCourse"
            value-key="name"
            :fetch-suggestions="listCoursesByName"
            @select="handleCourseSelect"
            placeholder="输入课程名称"
            style="width: 100%"
          ></el-autocomplete>
        </el-form-item>
      </el-form>

      <el-card v-if="selectedCourse.id">
        <el-form ref="form" :model="selectedCourse" label-width="100px" disabled>
          <el-form-item label="课程名称" prop="name">
            <el-input v-model="selectedCourse.name" placeholder="请输入课程名称"></el-input>
          </el-form-item>
          <el-form-item label="教练" prop="coach">
            <el-input v-model="selectedCourse.coach" placeholder="请输入教练"></el-input>
          </el-form-item>
          <el-form-item label="时长（分钟）" prop="duration">
            <el-input v-model="selectedCourse.duration" placeholder="请输入时长"></el-input>
          </el-form-item>
          <el-form-item label="价格（元）" prop="price">
            <el-input v-model="selectedCourse.price" placeholder="请输入价格"></el-input>
          </el-form-item>
        </el-form>
      </el-card>

      <!-- 操作按钮 -->
      <div style="margin-top: 20px">
        <el-button type="primary" @click="submitBuy(0)">现金支付</el-button>
        <el-button type="primary" @click="submitBuy(1)">刷卡支付</el-button>
        <el-button type="primary" @click="submitBuy(2)">支付宝支付</el-button>
        <el-button type="primary" @click="submitBuy(3)">微信支付</el-button>
      </div>
    </Layer>
    <Layer :layer="exchangeLayer" @confirm="submitExchange">
      <el-form :model="reward" label-width="100px">
        <el-form-item label="奖品*" prop="reward">
          <el-autocomplete
            v-model="queryReward"
            value-key="name"
            :fetch-suggestions="listRewardsByName"
            @select="handleRewardSelect"
            placeholder="输入奖品名称"
            style="width: 100%"
          ></el-autocomplete>
        </el-form-item>
      </el-form>

      <el-card v-if="selectedReward.id">
        <el-form ref="form" :model="selectedReward" label-width="100px" disabled>
          <el-form-item label="奖品名称" prop="name">
            <el-input v-model="selectedReward.name" placeholder="请输入奖品名称"></el-input>
          </el-form-item>
          <el-form-item label="所需积分" prop="points">
            <el-input v-model="selectedReward.points" placeholder="请输入所需积分"></el-input>
          </el-form-item>
        </el-form>
      </el-card>
    </Layer>
  </div>
</template>

<script setup>
import { login } from "@/api/member";
import { listCoursesByName, buyCourse } from "@/api/course";
import { listRewardsByName, exchangeReward } from "@/api/points";

import { ElMessage } from "element-plus";
import { reactive, ref } from "vue";
import Layer from "@/components/layer/index.vue";

import { useRoute, useRouter } from "vue-router";
const route = useRoute();
const router = useRouter();
const { id } = route.params;

const card = ref({});
if (id) {
  login(id)
    .then(res => {
      card.value = res;
    })
    .catch(() => {
      router.push("/");
    });
} else {
  router.push("/");
}

const memberLayer = reactive({
  show: false,
  title: "查看会员信息",
  showButton: false
});

const buyCourseLayer = reactive({
  show: false,
  title: "购买课程",
  showButton: false
});

const exchangeLayer = reactive({
  show: false,
  title: "积分兑换",
  showButton: true
});

const course = ref({});
const queryCourse = ref("");
const selectedCourse = ref({});
function handleCourseSelect(item) {
  selectedCourse.value = item;
}

// 提交购买课程信息
function submitBuy(payType) {
  buyCourse({
    memberId: card.value.memberId,
    courseId: selectedCourse.value.id,
    payType
  }).then(() => {
    ElMessage.success("课程购买成功");
    buyCourseLayer.show = false;
  });
}

const reward = ref({});
const queryReward = ref("");
const selectedReward = ref({});
function handleRewardSelect(item) {
  selectedReward.value = item;
}

// 提交积分兑换信息
function submitExchange() {
  exchangeReward({
    memberId: card.value.memberId,
    rewardId: selectedReward.value.id
  }).then(() => {
    ElMessage.success("积分兑换成功");
    exchangeLayer.show = false;
  });
}
</script>
