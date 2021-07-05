package com.wzl.j8new.test;

import org.openjdk.jol.info.ClassLayout;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wangzhilong
 * @date 2020/11/9 17:17
 * @Description: 测试对象内存布局 需引入jar包
 */
public class TestObjectsLayout {
    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
