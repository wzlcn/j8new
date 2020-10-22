package com.wzl.j8new.test;

/**
 * @author wangzhilong
 * @date 2020/7/22 9:30
 * @Description: 枚举类测试
 */
public enum EnumTest {

    MONTH1("month","1"),
    MONTH2("month","2");
    private String name;
    private String value;

    EnumTest(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static void main(String[] args) {
        System.out.println(EnumTest.MONTH1.getValue());
    }
}
