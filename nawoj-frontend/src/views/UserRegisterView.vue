<template>
  <div
    class="regisrer"
    style="display: flex; justify-content: center; align-items: center; height: 90vh"
  >
    <a-form :model="form" :style="{ width: '600px', marginTop: '20px' }" @submit="submit">
      <a-form-item
        field="用户名"
        label="用户名"
        required
        
      >
        <a-input
          v-model="form.userAccount"
          placeholder="请输入用户名"
        />
      </a-form-item>
      <a-form-item
        field="密码"
        label="密码"
        required
        
      >
        <a-input-password v-model="form.userPassword" placeholder="请输入密码" />
      </a-form-item>
      <a-form-item
        field="确认密码"
        label="再次输入密码"
        required
        
      >
        <a-input-password
          v-model="form.checkPassword"
          placeholder="请再次输入密码"
        />
      </a-form-item>
      <a-form-item field="avatar" label="头像">
        <a-upload
          action="http://localhost:8121/api/file/uploadAvatar"
          @change="onChange"
          @progress="onProgress"
          @success="onSuccess"
          :show-file-list="false"
        >
          <template #upload-button>
            <div
              :class="`arco-upload-list-item${
                file && file.status === 'error' ? ' arco-upload-list-item-error' : ''
              }`"
            >
              <div
                class="arco-upload-list-picture custom-upload-avatar"
                v-if="file && file.url"
              >
                <img :src="file.url" />
                <div class="arco-upload-list-picture-mask">
                  <IconEdit />
                </div>
                <a-progress
                  v-if="file.status === 'uploading' && file.percent < 100"
                  :percent="file.percent"
                  type="circle"
                  size="mini"
                  :style="{
                    position: 'absolute',
                    left: '50%',
                    top: '50%',
                    transform: 'translateX(-50%) translateY(-50%)',
                  }"
                />
              </div>
              <div class="arco-upload-picture-card" v-else>
                <div class="arco-upload-picture-card-text">
                  <IconPlus />
                  <div style="margin-top: 10px; font-weight: 200"></div>
                </div>
              </div>
            </div>
          </template>
        </a-upload>
      </a-form-item>
      <a-form-item>
        <a-button type="primary"  html-type="submit">注册</a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script setup lang="ts">
import { UserControllerService } from "@/generated";
import { useRouter } from "vue-router";

import { Message } from "@arco-design/web-vue";
import { reactive, ref } from "vue";

const userNameStatus = ref("warning");
const passwordStatus = ref("warning");
const passwordCheckStatus = ref("warning");
const router = useRouter()
const file = ref();

const form = reactive({
  userAccount: "",
  userPassword: "",
  checkPassword: "",
  userAvatar: file.value,
});
 
// const handleCheckPasswordChange = () => {

// };
// const handlePasswordChange = () => {


// };
// const handleUserNameChange = () => {


// };
const onSuccess = (file)=>{
  form.userAvatar=file.response.data
}

const submit = async() => {
  const res = await UserControllerService.userRegisterUsingPost(form);
  if(res.code===0){
    Message.success('注册成功')
     

    setTimeout(() => {
      router.push('/user/login')
    }, 1000);
  }else{
    Message.error(res.message)
  }
};

const onChange = (_, currentFile) => {

  file.value = {
    ...currentFile,
    // url: URL.createObjectURL(currentFile.file),
  };

};
const onProgress = (currentFile) => {
  file.value = currentFile;
};
</script>
<style scoped>
.regisrer {
  background: url("https://gw.alipayobjects.com/zos/rmsportal/FfdJeJRQWjEeGTpqgBKj.png")
    0% 0% / 100% 100%;
}
</style>
