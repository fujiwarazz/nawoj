 import { RouteRecordRaw } from "vue-router";
import HomeView from "../views/HomeView.vue";
import AboutViewVue from "@/views/AboutView.vue";
import notFound from "@/views/NotFoundView.vue"
import UserLoginViewVue from "@/views/UserLoginView.vue";
import UserRegisterView from '@/views/UserRegisterView.vue'
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
            access: 0,
            hide:true
        },

    },
    
    {
        path: "/problem",
        name: "问题",
        component: AboutViewVue,
        meta: {
            access: 0 as number
        },
    },
    {
        path: "/status",
        name: "状态",
        component: AboutViewVue,
        meta: {
            access: 0 as number
        },
    },
    {
        path: "/ranking",
        name: "排名",
        component: AboutViewVue,
        meta: {
            access: 0 as number
        },
    },
    {
        path: "/notification",
        name: "公告",
        component: AboutViewVue,
        meta: {
            access: 1 as number
        },
    },  
    {
        path: "/statistics",
        name: "统计",
        component: AboutViewVue,
        meta: {
            access: 2 as number
        },
    },  
    {
        path: '/404',
        name: 'not found',
        component: notFound,
        meta: {
            access: 0 as number,
            hide:true
        },
    },
    {
        path: "/user/login",
        name: "登录",
        component: UserLoginViewVue,
        meta: {
            access: 0 as number,
            hide:true
        },
    },
    {
        path: "/user/register",
        name: "注册",
        component: UserRegisterView,
        meta: {
            access: 0 as number,
            hide:true
        },
    },
    {
        path: "/user/profile",
        name: "用户中心",
        component: AboutViewVue,
        meta: {
            access: 1 as number,
            hide:true

        },
    },
    {
        path: '/:catchAll(.*)',
        redirect: '404'
    }
];

