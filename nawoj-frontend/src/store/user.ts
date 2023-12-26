import { StoreOptions } from "vuex"


export default {
    namespaced:true,
    state: () => ({
        loginUser: {
            userName: '未登录',
            access:0,

        },
    }),

    getters : {},

    //async
    actions:{
        getLoginUser({ commit,state}, payload) {

            commit("updateUser", payload)
        }
    },

    //sync
    mutations: {
        updateUser(state, payload) {
            state.loginUser = payload
        }
    },




} as StoreOptions<any>