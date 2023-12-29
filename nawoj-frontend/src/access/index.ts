
import { Modal } from "@arco-design/web-vue";
import router from "@/router";
import store from "@/store";
import accessEnum from "./accessEnum";



router.beforeEach(async (to, from, next) => {


    const cacheLoginUser = JSON.parse(localStorage.getItem("loginUser") || "{}");

    if (cacheLoginUser!=null) {


        await store.dispatch("user/getLoginUser", {
            userName: cacheLoginUser.userName,
            access: cacheLoginUser.access,
            userAvatar: cacheLoginUser.userAvatar,
            userProfile: cacheLoginUser.userProfile,
            id: cacheLoginUser.id
        })


    }
    const loginUser = store.state.user.loginUser

    if (!loginUser || loginUser.access === accessEnum.ACCESS_ENUM.NOT_LOGIN) {
        await store.dispatch('user/getLoginUser')
        console.log('auto login')
    }


    const needAccess: number = to.meta.access as number

    // console.log("test",loginUser?.access)


    if (loginUser?.access === -1) {
        //
        Modal.error({
            title: "错误提醒",
            content: "您的账号已被封禁 :(",
        });
        next(`/user/login?redirect=${to.fullPath}`)
    }
    //不需要登陆
    if (needAccess === 0) {
        next();
    }
    else if (needAccess > 0) {
        if (loginUser?.access >= needAccess) {
            next();
        } else if (loginUser?.access == accessEnum.ACCESS_ENUM.NOT_LOGIN ) {
            console.log('not login')
            next(`/user/login?redirect=${to.fullPath}`)
        } else {

            Modal.error({
                title: "错误提醒",
                // content: "权限不足 :(",
                content: `${loginUser?.access}`
            });
            next(`/user/login?redirect=${to.fullPath}`)
        }
    }
});
