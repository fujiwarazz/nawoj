<template>
  <div id="app"></div>
  <BasicLayout />
</template>

<script setup>
import BasicLayout from "@/layouts/BasicLayout.vue";
import { useRoute, useRouter } from "vue-router";
import { useStore } from "vuex";
import { Modal } from "@arco-design/web-vue";


const router = useRouter();
const route = useRoute();
const store = useStore();

/**
 * 全局初始化
 */
const initFunc() =>{

}

onMounted(() => {
    initFunc()
})


router.beforeEach((to, from, next) => {
  console.log(to.meta.access);
  if (store.state.user?.loginUser?.access >= to.meta.access) {
    next();
  } else {
    Modal.error({
      title: "Error Notification",
      content: "您的权限不足:(",
    });
    router.push("/home");
  }
});
</script>
<style></style>
QWWW QWEASDWASZXXXSWSXDW 