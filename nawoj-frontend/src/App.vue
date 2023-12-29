<template>
  <div id="app"></div>
  <BasicLayout />
</template>

<script setup>
import BasicLayout from "@/layouts/BasicLayout.vue";
import { useRoute, useRouter } from "vue-router";
import { useStore } from "vuex";
// import { onBeforeMount } from "vue";

const router = useRouter();
const route = useRoute();
const store = useStore();

/**
 * 全局初始化
 */
// const initFunc() =>{

// }

// onBeforeMount(() => {
 
// });
</script>
<style></style>
