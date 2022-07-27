package com.wzl.j8new.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangzhilong
 * @date 2020/7/17 8:56
 * @Description: App实体类
 */
@Data
public class App implements Serializable {
    private Integer id;
    private String appVersion;
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//前端传参为表单
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")//前端传参为json
    private Date time;

    public App(String appVersion, Date time) {
        this.appVersion = appVersion;
        this.time = time;
    }
}
