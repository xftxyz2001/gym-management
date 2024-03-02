<template>
  <div class="layout-container">{{ member }}</div>
</template>

<script setup>
import { getMember } from "@/api/member";
import { ref } from "vue";

import { useRoute } from "vue-router";
const route = useRoute();
const { id } = route.params;

const member = ref({});
if (id) {
  getMember(id).then(res => {
    member.value = res;
  });
}
</script>
