<template>
  <div id="basicLayout">
    <a-layout>
      <a-layout-header class="header">
        <NavBar/>
      </a-layout-header>

      <a-layout-content class="content">
        <router-view />

      </a-layout-content>


      <a-layout-footer class="footer">
        <a href="https://github.com/fujiwarazz?tab=repositories" target="_blank" rel="noopener noreferrer">
          <icon-github style="font-size: 20px;"/>
        Power By Peelsannaw</a>
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
  position: sticky;
  top: 0px;
  left: 0px;
  right: 0px;

}
 .content {
 } 
.footer {
  background: #efefef;
  padding: 10px;
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  text-align: center;
  font-size: 14px;
}
</style>
