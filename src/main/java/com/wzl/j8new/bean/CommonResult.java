package com.wzl.j8new.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author wzlong
 * @Date 2021/9/26 10:23
 * @Description:
 */
@Data
public class CommonResult implements Serializable {
    private int code;
    private String message;


    public CommonResult() {
    }

    public CommonResult(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
