package com.wzl.j8new.test;



import com.wzl.j8new.bean.User;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        List<User> users = Arrays.asList(new User("张三", "20"),
                new User("张三", "20"),
                new User("李四", "20"),
                new User("王五", "20"));
        Map<String, List<User>> collect = users.stream().sorted(Comparator.comparing(User::getUserName)).collect(Collectors.groupingBy(User::getFirstAlphabet));
        System.out.println(collect);
    }
}
