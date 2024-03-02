<template>
  <div class="layout-container">{{ card }}</div>
</template>

<script setup>
import { getCard } from "@/api/member";
import { ref } from "vue";

import { useRoute } from "vue-router";
const route = useRoute();
const { id } = route.params;

const card = ref({});
if (id) {
  getCard(id).then(res => {
    card.value = res;
  });
}
</script>
