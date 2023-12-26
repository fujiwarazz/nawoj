<template>
  <div id="basicLayout">
    <a-layout style="height: 400px">
      <a-layout-header class="header">
        <NavBar/>

      </a-layout-header>
      <a-layout-content class="content">
        <router-view />
      </a-layout-content>
      <a-layout-footer class="footer">
        <a href="https://github.com/fujiwarazz?tab=repositories" target="_blank" rel="noopener noreferrer">Power By Peelsannaw</a>
      </a-layout-footer>
    </a-layout>
  </div>
</template>

<script setup >
import NavBar from'../components/NavBar'

</script>

<style scoped>
#basicLayout {
  font-size: 20px;
}
.header {
  
}
.content {
  background:linear-gradient(to right, #939393,#838228);
}
.footer {
  background: #efefef;
  padding: 16px;
  position: absolute;
  bottom: 0px;
  left:0px;
  right:0px;
  text-align: center;
  font-size: 14px;
}
</style>
