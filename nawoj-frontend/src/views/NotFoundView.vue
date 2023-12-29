<template>
  <a-result status="404" subtitle="不存在的页面">
    <template #extra>
      <a-space>
        <a-button type="primary" @click="returnHome">将在 {{ leftTime }} 后返回主页</a-button>
      </a-space>
    </template>
  </a-result>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { useRouter } from "vue-router";

const router = useRouter();
const leftTime = ref(3);

const returnHome = ()=>{
  router.push('/home')
}
const autoReturn = ()=>{

  const time = setInterval(()=>{
    leftTime.value-=1;
  },1000)


  const time2 = setTimeout(() => {
    router.push('/home')
  }, 3000);

}
autoReturn()

</script>
