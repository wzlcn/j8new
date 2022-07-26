package com.wzl.j8new;

import com.wzl.j8new.service.AopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class J8newApplicationTests {

    @Autowired
    private AopService aopService;
    @Test
    void contextLoads() {
        aopService.testAopInterface();
    }

}
