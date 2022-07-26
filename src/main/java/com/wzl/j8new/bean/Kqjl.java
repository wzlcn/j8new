package com.wzl.j8new.bean;

import lombok.Data;

import java.util.Date;

/**
 * @Author wzlong
 * @Date 2021/9/26 10:52
 * @Description:
 */
@Data
public class Kqjl {
    private int staffNum;
    private String staffName;
    private String staffDepartment;
    private Date checkinTime;
    private Date begainTime;
    private Date endTime;
    private String weekOfDay;
    //0 工作日 1 非工作日
    private String isWorkDay;
}
