package com.wzl.j8new.controller;

import com.wzl.j8new.bean.App;
import com.wzl.j8new.service.AppService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangzhilong
 * @date 2020/7/17 10:07
 * @Description:
 */
@RestController
@RequestMapping(value = "app")
public class AppController {

    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private AppService appService;
    @Autowired
    HttpServletRequest request;

    @RequestMapping(value = "insert")
    public void insert(@RequestBody App app) {
        logger.info("---------------进入insert方法");
        String flag = (String) request.getAttribute("flag");
        if (StringUtils.equals(flag,"invalid")){
            logger.info("ip非法，即将退出");
            return;
        }
        appService.insert(app);
        logger.info("新增成功");
    }
}
