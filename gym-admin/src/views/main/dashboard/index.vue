<template>
  <div class="box">
    <div>欢迎使用健身房管理系统</div>
    <Card />
    <!-- <Charts /> -->
    <Home />
  </div>
</template>

<script lang="js">
import { defineComponent } from "vue";
import Card from "./components/card/index.vue";
// import Charts from './components/charts/index.vue'
import Home from "./components/home/index.vue";
export default defineComponent({
  components: {
    Card,
    // Charts,
    Home
  }
});
</script>

<style lang="scss" scoped>
.box {
  padding: 15px;
}
</style>
