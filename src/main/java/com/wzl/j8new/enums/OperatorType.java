package com.wzl.j8new.enums;


public enum OperatorType
{
    /**
     * 其它
     */
    OTHER("其它",0),

    /**
     * 后台用户
     */
    MANAGE("后台用户",1),

    /**
     * 手机端用户
     */
    MOBILE("手机端用户",2);

    OperatorType(String type, int code) {
        this.type = type;
        this.code = code;
    }

    private String type;
    private int code;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
