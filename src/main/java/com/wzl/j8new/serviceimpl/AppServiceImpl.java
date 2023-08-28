package com.wzl.j8new.serviceimpl;

import com.wzl.j8new.bean.App;
import com.wzl.j8new.dao.AppMapper;
import com.wzl.j8new.service.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author wangzhilong
 * @date 2020/7/17 9:01
 * @Description:
 */
@Service
public class AppServiceImpl implements AppService {
    private static final Logger logger = LoggerFactory.getLogger(AppServiceImpl.class);

    @Autowired
    private AppMapper appMapper;
    @Autowired
    private AsyncOperation asyncOperation;

    @Override
    public void insert(App app) {
        logger.info("----------开始执行新增方法");
        logger.info("当前线程名称：" + Thread.currentThread().getName());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        asyncOperation.testAsyncException();
        appMapper.insert(app);
    }

    @Override
    public List<Map> query() {
        return appMapper.query();
    }

    @Override
    public List<Map> query2() {
        return appMapper.query2();
    }
}
