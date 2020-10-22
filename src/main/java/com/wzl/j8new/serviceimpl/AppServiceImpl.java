package com.wzl.j8new.serviceimpl;

import com.wzl.j8new.bean.App;
import com.wzl.j8new.dao.AppMapper;
import com.wzl.j8new.service.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public void insert(App app) {
        logger.info("----------开始执行新增方法");
        appMapper.insert(app);
    }
}
