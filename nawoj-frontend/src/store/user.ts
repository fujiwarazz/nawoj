import accessEnum from "@/access/accessEnum"
import { UserControllerService } from "@/generated"

import { StoreOptions } from "vuex"


export default {
    namespaced:true,
    state: () => ({
        loginUser: {
            userName: '未登录',
            access:accessEnum.ACCESS_ENUM.NOT_LOGIN,
            userAvatar:'https://cdn-mii.accounts.nintendo.com/2.0.0/mii_images/bea740be86a52acc/f78ed1356367c2f2bd6da2c0f68511c9e88ebdfb.png?type=face&width=140&bgColor=DFDFDFFF',
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
          
        }
    },




} as StoreOptions<any>