import { RouteRecordRaw } from "vue-router";
import HomeView from "../views/HomeView.vue";
import AboutViewVue from "@/views/AboutView.vue";
import notFound from "@/views/NotFoundView.vue"



/*
    0：游客
    1：普通用户
    2：管理员
*/
export const routes: Array<RouteRecordRaw> = [
    {
        path: "/home",
        name: "主页",
        component: HomeView,
        meta: {
            access: 0
        },

    },
    
    {
        path: "/problem",
        name: "问题",
        component: AboutViewVue,
        meta: {
            access: 0
        },
    },
    {
        path: "/status",
        name: "状态",
        component: AboutViewVue,
        meta: {
            access: 0
        },
    },
    {
        path: "/ranking",
        name: "排名",
        component: AboutViewVue,
        meta: {
            access: 0
        },
    },
    {
        path: "/notification",
        name: "公告",
        component: AboutViewVue,
        meta: {
            access: 1
        },
    },  
    {
        path: '/404',
        name: 'not found',
        component: notFound,
        meta: {
            access: 0
        },
    },
    {
        path: '/:catchAll(.*)',
        redirect: '404'
    }
];

