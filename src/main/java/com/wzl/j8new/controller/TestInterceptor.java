package com.wzl.j8new.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangzhilong
 * @date 2020/9/10 14:39
 * @Description: 测试拦截器
 */
@RestController
@RequestMapping(value = "test")
public class TestInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(TestInterceptor.class);

    @RequestMapping(value = "test")
    public void test(){
        logger.info("进入测试拦截器方法");
    }
}
