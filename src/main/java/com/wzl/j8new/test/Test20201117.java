package com.wzl.j8new.test;

import com.wzl.j8new.bean.User;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.reducing;

/**
 * @author wangzhilong
 * @date 2020/11/17 14:14
 * @Description:
 */
public class Test20201117 {
    public static void main(String[] args) {
        List<User> users = Arrays.asList(new User("小二", "20"),
                new User("张三", "21"),
                new User("李四", "22"),
                new User("王五", "23"));
        List<User> use = Arrays.asList(new User("小二", "20"));
        /*List<Integer> list = Arrays.asList(1, 2, 1, 3, 2, 4, 5);
        list.stream()
            .filter(e -> e % 2 == 0)
//            .distinct()                   //去重重复元素
            .forEach(System.out::println);*/

        /*List<String> strings = Arrays.asList("Hello", "World");
        strings.stream()
                .map(e -> e.split(""))
                //Arrays::stream将一个数组转换成流
                //flatMap将多个流扁平化为单个流
                .flatMap(Arrays::stream)
                .distinct()
                .forEach(System.out::println);*/

        /*List<Integer> num1 = Arrays.asList(1, 2, 3);
        List<Integer> num2 = Arrays.asList(4,5);
        List<int[]> ints = num1.stream()
                .flatMap(i -> num2.stream()             //将两个流合并为一个
                        .filter(j -> (i + j) % 3 == 0)  //过滤和能被3整除的
                        .map(j -> new int[]{i, j})      //得到新数组
                ).collect(Collectors.toList());
        for (int[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
        }*/

        List<Integer> list = Arrays.asList(3, 3, 9, 6, -1);
        /*Integer reduce = list.stream()
                .reduce(1, (a,b) -> a * b);             //reduce归约操纵，将流中得元素通过某种方法处理后得到一个值或一个元素
        System.out.println(reduce);*/
        /*Integer reduce = list.stream()
                //reduce第二个参数也可以使用三目表达式 (a,b) -> a > b ? a : b
                .reduce(list.get(0), Integer::max);       //reduce 第一个参数是初始值，第二个参数接受一个lambda表达式将两个元素转换成一个元素
        System.out.println(reduce);*/

        /*Optional<User> user = users.stream()
                .collect(reducing((u1, u2) -> Integer.valueOf(u1.getAge()) > Integer.valueOf(u2.getAge()) ? u1 : u2));
        User older = user.get();
        System.out.println(older);*/

        List<User> collect = users.stream().filter(user -> {
            for (User user1 : use) {
                if (StringUtils.equals(user1.getUserName(), user.getUserName())) {
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toList());

        System.out.println(collect);
    }
}
