package com.wzl.j8new.enums;


public enum BusinessType
{
    /**
     * 其它
     */
    OTHER("其它",0),

    /**
     * 新增
     */
    INSERT("新增",1),

    /**
     * 修改
     */
    UPDATE("修改",2),

    /**
     * 删除
     */
    DELETE("删除",3),

    /**
     * 授权
     */
    GRANT("授权",4),

    /**
     * 导出
     */
    EXPORT("导出",5),

    /**
     * 导入
     */
    IMPORT("导入",6),

    /**
     * 强退
     */
    FORCE("强退",7),

    /**
     * 生成代码
     */
    GENCODE("生成代码",8),

    /**
     * 清空数据
     */
    CLEAN("清空数据",9),
    /**
     * 查询
     */
    QUERY("查询",10)
    ;

    BusinessType(String type, int code) {
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
