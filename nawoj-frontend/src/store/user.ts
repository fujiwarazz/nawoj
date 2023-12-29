import accessEnum from "@/access/accessEnum"
import { UserControllerService } from "@/generated"

import { StoreOptions } from "vuex"


export default {
    namespaced:true,
    state: () => ({
        loginUser: {
            userName: '未登录',
            access:accessEnum.ACCESS_ENUM.NOT_LOGIN,
            userAvatar:'',
            userProfile:'',
            id:-1
        },
    }),

    getters : {},

    //async
    actions:{
        async getLoginUser({ commit,state}, payload) {
            const res = await UserControllerService.getLoginUserUsingGet();
            console.log(res)
            if(res.code===0){
                console.log(res.data)
                commit("updateUser", res.data)
            }else{
                commit("updateUser",{
                    ...state.loginUser,
                    access:accessEnum.ACCESS_ENUM.NOT_LOGIN
                })

            }
        }
    },

    //sync
    mutations: {
        updateUser(state, payload) {
            state.loginUser = payload
            console.log("now loginUser state:")
            console.log(state.loginUser)
        }
    },




} as StoreOptions<any>