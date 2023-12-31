<template>
  <div class="nav">
    <a-row
      id="globalNav"
      class="grid-demo"
      style="margin-bottom: 0px; width: 100%; background: white"
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
      <a-col flex="200px">
        <div
          class="user"
          v-if="store.state.user?.loginUser?.access >= accessEnum.ACCESS_ENUM.NORMAL_USER"
        >
          <span class="name">{{ store.state.user.loginUser.userName }}</span>
          <!-- <a-avatar :size="40"
            ><img alt="avatar"  :src="store.state.user.loginUser?.userAvatar" />
          </a-avatar> -->
          <a-dropdown style="font-size: 16px;  ">
            <a-avatar :size="40"
            ><img alt="avatar"  :src="store.state.user.loginUser?.userAvatar" />
          </a-avatar>
            <template #content>
              
              <a-doption @click="goUserCenter">
                <icon-idcard style="font-size: 20px;"/>
                  用户中心
               
              </a-doption>
              <a-doption @click="logout">
                 <icon-export style="font-size: 20px;" />
                退出登录
              </a-doption>
            </template>
          </a-dropdown>

        </div>

        <div class="user" v-else>
          <a-dropdown style="font-size: 16px;  ">
            <a-avatar class="noLogin" :style="{ backgroundColor: '#00d0b6' }"
              >未登录</a-avatar
            >
            <template #content>
              <a-doption @click="goLogin">
                <icon-import style="font-size: 20px;"/>
                  去登陆
            
              </a-doption>
              <a-doption @click="goRegister">
                <icon-idcard style="font-size: 20px;"/>
                  去注册
               
              </a-doption>
            </template>
          </a-dropdown>
        </div>
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
import nprogress from "nprogress";
import "nprogress/nprogress.css";
import { UserControllerService } from "@/generated";

const store = useStore();
const router = useRouter();
const route = useRoute();
const selectKey = ref([route.path]);

//computed监听组件变化
const visableRoutes = computed(() => {
  return routes.filter((item) => {
    let role: number = item.meta?.access as number;
    console.log(store.state.user?.loginUser?.access);
    if (item.meta?.hide || store.state.user?.loginUser?.access < role) {
      return false;
    }
    return true;
  });
});
const logout = async()=>{
  const res = await UserControllerService.userLogoutUsingPost()
  if(res.code === 0){
    router.push('/user/login')
  }
}
const goLogin = () => {
  router.push("/user/login");
};
router.afterEach((to) => {
  selectKey.value = [to.path];
  nprogress.done()

});

const doMenuClick = (key: string) => {
  router.push({
    path: key,
  });
};
const goRegister = () => {
  router.push("/user/register");
};
// dispatch调用actions
// setTimeout(() => {
//   console.log(store.state.user.loginUser.userName);
// store.dispatch("user/getLoginUser", {
//   userName: "peelsannaw",
//   access: accessEnum.ACCESS_ENUM.NORMAL_USER,
// });
// }, 1000);
</script>

<style scoped>
#globalNav {
  width: 100vw;
}
.nav {
  border-bottom: lightgray 1px solid;
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
  padding: 10px;
  display: inline;
}
.name {
  margin-right: 10px;
  color: rgb(60, 60, 60);
}
.noLogin {
  margin-left: 100px;
}
</style>
