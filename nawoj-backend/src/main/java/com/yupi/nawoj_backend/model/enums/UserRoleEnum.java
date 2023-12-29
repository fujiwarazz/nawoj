package com.yupi.nawoj_backend.model.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.ObjectUtils;

/**
 * 用户角色枚举
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
public enum UserRoleEnum {

    GUEST("游客", "guest", 0),
    USER("用户", "user", 1),
    ADMIN("管理员", "admin", 2),
    BAN("被封号", "ban", -1);

    private final String text;

    private final String value;

    private final Integer code;

    UserRoleEnum(String text, String value, Integer code) {
        this.text = text;
        this.value = value;
        this.code = code;
    }

    /**
     * 获取值列表
     *
     * @return
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }
    @SuppressWarnings("all")
    public static String getValueByUserRoleEnumCode(UserRoleEnum userRoleEnum){
        switch (userRoleEnum.code){
            case -1: return BAN.getValue();
            case 0: return GUEST.getValue();
            case 1: return USER.getValue();
            case 2: return ADMIN.getValue();
            default: return GUEST.getValue();
        }

    }
    public static Integer getUserRoleCodeByValue(String value){
        switch (value){
            case "user": return USER.code;
            case "admin": return ADMIN.code;
            case "ban": return BAN.code;
            default: return GUEST.code;
        }
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static UserRoleEnum getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (UserRoleEnum anEnum : UserRoleEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public Integer getCode() {
        return code;
    }

}
