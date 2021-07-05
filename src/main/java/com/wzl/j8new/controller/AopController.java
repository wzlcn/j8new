package com.wzl.j8new.controller;

import com.wzl.j8new.service.AopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangzhilong
 * @date 2021/7/5 15:02
 * @Description:
 */
@RestController
@RequestMapping(value = "aop")
public class AopController {

    @Autowired
    private AopService aopService;

    @RequestMapping(value = "aop11")
    public void aop11(){
        System.out.println("aop start--------");
        aopService.testAopInterface();
    }
}
