package com.wzl.j8new.enums;


public enum BusinessStatus
{
    /**
     * 成功
     */
    SUCCESS("成功",0),

    /**
     * 失败
     */
    FAIL("失败",1)
    ;

    BusinessStatus(String status, int code) {
        this.status = status;
        this.code = code;
    }

    private String status;
    private int code;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
