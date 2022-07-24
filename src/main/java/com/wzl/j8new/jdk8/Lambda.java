package com.wzl.j8new.jdk8;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

public class Lambda {
    public static void main(String[] args) {
        ArrayList<String> list = Lists.newArrayList("abc", "aa", "asfsfd");
//        Collections.sort(list, Comparator.comparingInt(String::length));
        list.sort(Comparator.comparingInt(String::length));
        System.out.println(list);

        //flatMap升维
        List<Integer> integer1 = Arrays.asList(1, 2, 3);
        List<Integer> integer2 = Arrays.asList(3, 4, 5);
        List<int[]> collect = integer1.stream().flatMap(x -> integer2.stream().map(y -> new int[]{x, y})).collect(Collectors.toList());
        System.out.println(JSON.toJSON(collect));

        //flatMap降维
        List<String> strings = Arrays.asList("Hello", "World");
        strings.stream().map(x -> x.split("")).flatMap(Arrays::stream).forEach(y -> System.out.println(y));
    }
}
