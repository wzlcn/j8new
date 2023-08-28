package com.wzl.j8new.service;

import com.wzl.j8new.bean.App;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author wangzhilong
 * @date 2020/7/17 8:59
 * @Description:
 */
public interface AppService {
    public void insert(App app);

    public List<Map> query();

    public List<Map> query2();
}
