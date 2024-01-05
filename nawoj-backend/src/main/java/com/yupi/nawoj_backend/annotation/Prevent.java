package com.yupi.nawoj_backend.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author peelsannaw
 * @create 19/12/2022 下午4:27
 * 接口防刷,默认60s一次
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Prevent {

    /**
     * 限制时间
     * @return
     */
    String value() default "60";

    /**
     * 提示信息
     * @return
     */
    String message() default "请稍后再试!";

    PreventStrategy strategy() default PreventStrategy.DEFAULT;
}
