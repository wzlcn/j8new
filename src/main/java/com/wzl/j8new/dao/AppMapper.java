package com.wzl.j8new.dao;

import com.wzl.j8new.bean.App;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author wangzhilong
 * @date 2020/7/17 9:02
 * @Description:
 */
@Mapper
public interface AppMapper {
    void insert(App app);
    List<Map> query();
    List<Map> query2();
}
