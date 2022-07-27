package com.wzl.j8new.serviceimpl;

import com.wzl.j8new.aop.Log;
import com.wzl.j8new.bean.App;
import com.wzl.j8new.dao.AppMapper;
import com.wzl.j8new.enums.BusinessType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author wzlong
 * @Date 2022/7/27 9:14
 * @Description:
 */
@Component
public class AsyncOperation {

    private static Logger logger = LoggerFactory.getLogger(AsyncOperation.class);

    @Autowired
    private AppMapper appMapper;

    @Async("threadPoolTaskExecutor")
//    @Log(title = "log测试2", businessType = BusinessType.INSERT)
    public void testAsyncException(){
        logger.info("异步线程名称：" + Thread.currentThread().getName());
        appMapper.insert(new App("3.0",new Date()));
    }
}
