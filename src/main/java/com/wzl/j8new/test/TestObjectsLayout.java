package com.wzl.j8new.test;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author wangzhilong
 * @date 2020/11/9 17:17
 * @Description: 测试对象内存布局 需引入jar包
 */
public class TestObjectsLayout {
    public static void main(String[] args) {
        Object o = new Object();
//        String name = "王志龙asdfg";
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
