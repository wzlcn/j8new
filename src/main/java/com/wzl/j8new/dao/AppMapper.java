package com.wzl.j8new.dao;

import com.wzl.j8new.bean.App;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wangzhilong
 * @date 2020/7/17 9:02
 * @Description:
 */
@Mapper
public interface AppMapper {
    void insert(App app);
}
