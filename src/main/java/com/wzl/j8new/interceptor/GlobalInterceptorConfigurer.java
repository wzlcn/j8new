package com.wzl.j8new.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wangzhilong
 * @date 2020/9/10 14:14
 * @Description: 拦截器全局配置
 */
@Configuration
public class GlobalInterceptorConfigurer implements WebMvcConfigurer {


    /**
     * 把我们的拦截器注入为bean
     * @return
     */
    @Bean
    public HandlerInterceptor requestInterceptor(){
        return new RequestInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor()).addPathPatterns("/app/*")

                ;
    }
}
