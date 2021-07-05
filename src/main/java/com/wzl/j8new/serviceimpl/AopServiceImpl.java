package com.wzl.j8new.serviceimpl;

import com.wzl.j8new.aop.AopTest;
import com.wzl.j8new.service.AopService;
import org.springframework.stereotype.Service;

/**
 * @author wangzhilong
 * @date 2021/7/5 15:10
 * @Description:
 */
@Service
public class AopServiceImpl implements AopService {

    @Override
    @AopTest
    public void testAopInterface() {
        System.out.println("====业务代码====");
    }
}
