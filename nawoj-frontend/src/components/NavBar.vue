<template>
  <div>
    <a-row
      id="globalNav"
      class="grid-demo"
      style="margin-bottom: 5px"
      align="center"
      :wrap="false"
    >
      <a-col flex="auto">
        <a-menu
          mode="horizontal"
          :selected-keys="selectKey"
          @menu-item-click="doMenuClick"
        >
          <a-menu-item key="/home" :style="{ padding: 0, marginRight: '38px' }">
            <div class="logo">
              <img
                :style="{
                  width: '25px',
                  height: '25px',
                }"
                src="https://xujcoj.com/favicon.ico"
              />
              <div class="logo_name"><router-link to="/home">NawOj</router-link></div>
            </div>
          </a-menu-item>

          <a-menu-item v-for="item in visableRoutes" :key="item.path">
            {{ item.name }}
          </a-menu-item>
        </a-menu>
      </a-col>
      <a-col flex="155px">
        <div class="user">{{ store.state.user?.loginUser?.userName ?? "未登录" }}</div>
      </a-col>
    </a-row>
  </div>
</template>
<script setup lang="ts">
import { useRoute, useRouter } from "vue-router";
import { routes } from "../router/routes";
import { computed, ref } from "vue";
import { useStore } from "vuex";
import accessEnum from "@/access/accessEnum";

// import nprogress from "nprogress";
// import "nprogress/nprogress.css";

const store = useStore();
const router = useRouter();
const route = useRoute();
const selectKey = ref([route.path]);


//computed监听组件变化
const visableRoutes = computed(() => {
  return routes.filter((item) => {
    let role:number = item.meta?.access as number;
    console.log(store.state.user?.loginUser?.access)
    if (item.meta?.hide || store.state.user?.loginUser?.access < role) {
      return false;
    }
    return true;
  });
});

router.afterEach((to) => {
  selectKey.value = [to.path];
});

const doMenuClick = (key: string) => {
  router.push({
    path: key,
  });
};

//dispatch调用actions
setTimeout(() => {
  console.log(store.state.user.loginUser.userName);
  store.dispatch("user/getLoginUser", {
    userName: "peelsannaw",
    access:accessEnum.ACCESS_ENUM.NORMAL_USER,
  });
}, 1000);
</script>

<style scoped>
#globalNav {
}
.logo {
  padding-left: 10px;
  display: inline-flex;
}
.logo > .logo_name {
  align-items: center;
  margin-left: 10px;
  font-size: 25px;
  color: #333333;
}
.user {
  font-size: 16px;
}
</style>
