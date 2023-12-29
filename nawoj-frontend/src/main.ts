import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import ArcoVue from '@arco-design/web-vue';
import '@arco-design/web-vue/dist/arco.css';
import "../src/utils/request.ts"
import "@/access/index.ts"
import ArcoVueIcon from '@arco-design/web-vue/es/icon';



createApp(App).use(store).use(router).use(ArcoVue).use(ArcoVueIcon).mount("#app");
