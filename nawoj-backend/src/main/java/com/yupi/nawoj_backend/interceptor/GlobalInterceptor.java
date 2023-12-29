package com.yupi.nawoj_backend.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author peelsannaw
 * @create 31/12/2023 11:02
 */
@Configuration
@Slf4j
public class GlobalInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if("OPTIONS".equals(request.getMethod())){
//            return true;
//        }
        log.info("请求路径:{}",request.getRequestURL());
        log.info("请求方法:{}",request.getMethod());
        return true;
    }
}
