<template>刷卡</template>

<script setup>
import { useRoute } from "vue-router";

const route = useRoute();

console.log(route.params.id);
</script>

