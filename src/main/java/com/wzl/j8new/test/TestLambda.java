package com.wzl.j8new.test;

import com.wzl.j8new.bean.User;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author wangzhilong
 * @date 2020/10/28 17:02
 * @Description:
 */
/*函数式接口：只有一个抽象方法 一般用@FunctionalInterface注解标识
java8中Function(apply(T,R) 将参数T转换为R类型)、Supplier(get() 获取结果集)、
Consumer(void accept() 对给定结果执行操作 相当于lambda的foreach())、Predicate(boolean test(T t) 相当于lambda的filter()) 4大函数式接口主要结合lambda表达式使用
1、Function<Integer, String> verboseLambda = (Integer x)-> { return Integer.toString(x*x); };//只有一行代码{}可以去掉
  System.out.println(verboseLambda.apply(5));
2、Supplier<Stream<User>> stream = users::stream;
   Stream<User> result = stream.get();

  */
public class TestLambda {
    public static void main(String[] args) {
//        List<String> list = Arrays.asList("a", "Z", "k", "c");
//        list.sort((s1,s2) -> s1.compareToIgnoreCase(s2));
//        list.stream().sorted(Comparator.comparing(String::length).reversed()).collect(Collectors.toList());
//        System.out.println(list);



        List<User> users = Arrays.asList(new User("张三", "20"),
                new User("张三", "20"),
                new User("李四", "20"),
                new User("王五", "20"));
        Stream<User> userStream = users.stream();
        Supplier<Stream<User>> stream = users::stream;
        Stream<User> result = stream.get();
    }
}
