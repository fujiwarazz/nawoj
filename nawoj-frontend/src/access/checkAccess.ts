

/**
 * check loginUser has the access
 * @param loginUser now login user
 * @param needAccess 
 */
import { useRoute, useRouter } from "vue-router";
import { useStore } from "vuex";
import { Modal } from "@arco-design/web-vue";
import ACCESS_ENUM from './accessEnum'
const router = useRouter();
const route = useRoute();
const store = useStore();

const checkAccess = (loginUser:any, needAccess = ACCESS_ENUM.ACCESS_ENUM.NOT_LOGIN) => {

    const loginUserAcess = loginUser?.access ?? ACCESS_ENUM.ACCESS_ENUM.NOT_LOGIN
    if (needAccess === ACCESS_ENUM.ACCESS_ENUM.NOT_LOGIN) {
        return true;
    }
    if (needAccess === ACCESS_ENUM.ACCESS_ENUM.NORMAL_USER) {
        if (loginUserAcess === ACCESS_ENUM.ACCESS_ENUM.NOT_LOGIN) {
            return false;
        }
    }
    if (needAccess === ACCESS_ENUM.ACCESS_ENUM.ADMIN) {
        if (loginUserAcess < ACCESS_ENUM.ACCESS_ENUM.NOT_LOGIN) {
            return false;
        }
    }
    return true;

}


