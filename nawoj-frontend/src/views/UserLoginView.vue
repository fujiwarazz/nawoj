<template>
  
    <div
      class="login"
      style="
        display: flex;
        justify-content: center;
        align-items: center;
         height: 90vh;
      "
    >
      <a-form :model="loginFrom" :style="{ width: '600px' ,margin:'0 auto'}" @submit="submit">
        <a-form-item field="userAccount" validate-trigger="input" required>
          <a-input v-model="loginFrom.userAccount" placeholder="请输入用户名">
            <template #prefix>
              <icon-user />
            </template>
          </a-input>
          <template #extra>
            <div></div>
          </template>
        </a-form-item>
        <a-form-item field="userPassword" validate-trigger="input" required>
          <a-input-password
            v-model="loginFrom.userPassword"
            placeholder="请输入用密码"
            allow-clear
          >
            <template #prefix>
              <icon-lock />
            </template>
          </a-input-password>
        </a-form-item>
        <a-form-item field="isRead">
          <a-checkbox style="color: silver" v-model="isRem">保存登录信息</a-checkbox>
        </a-form-item>
        <a-form-item>
          <a-button type="primary" style="margin: 0 auto;" html-type="submit">提交</a-button>
        </a-form-item>
      </a-form>
    </div>

 
</template>

<script setup lang="ts">
import { UserControllerService, UserLoginRequest } from "@/generated";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import message from "@arco-design/web-vue/es/message";
import { reactive, ref } from "vue";

const router = useRouter();
const store = useStore();
const isRem = ref(false);
const loginFrom = reactive({
  userAccount: "",
  userPassword: "",
} as UserLoginRequest);

const submit = async () => {

  const res = await UserControllerService.userLoginUsingPost(loginFrom);
  if (res.code === 0) {
    message.success("登陆成功");
    if (isRem.value === true) {
      localStorage.setItem("loginUser", JSON.stringify(res.data));
    }
    
    setTimeout(() => {
        store.dispatch("user/getLoginUser", {
        userName: res.data.userName,
        access:res.data.access
    });
        router.push("/home");

    }, 1000);
  } else if (res.code === 40000) {
    message.error(res.message);
  }
};
</script>

<style scoped>
.login{
    background: url('https://gw.alipayobjects.com/zos/rmsportal/FfdJeJRQWjEeGTpqgBKj.png') 0% 0% / 100% 100%;
    
}
</style>
