package com.wzl.j8new.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author wangzhilong
 * @date 2020/8/14 9:23
 * @Description: corn表达式测试
 */
@Component
public class CornTest {

    /*cron表达式语法:
            [秒][分][小时][日][月][周][年]
    注：[年]不是必须的域，可以省略[年]，则一共6个域*/
    /*有些子表达式能包含一些范围或列表
    例如：子表达式（天（星期））可以为 “MON-FRI”，“MON，WED，FRI”，“MON-WED,SAT”
            “*”字符代表所有可能的值
    因此，“*”在子表达式（月）里表示每个月的含义，“*”在子表达式（天（星期））表示星期的每一天
“/”字符用来指定数值的增量
    例如：在子表达式（分钟）里的“0/15”表示从第0分钟开始，每15分钟
    在子表达式（分钟）里的“3/20”表示从第3分钟开始，每20分钟（它和“3，23，43”）的含义一样
“？”字符仅被用于天（月）和天（星期）两个子表达式，表示不指定值
    当2个子表达式其中之一被指定了值以后，为了避免冲突，需要将另一个子表达式的值设为“？”
            “L” 字符仅被用于天（月）和天（星期）两个子表达式，它是单词“last”的缩写
    但是它在两个子表达式里的含义是不同的。
    在天（月）子表达式中，“L”表示一个月的最后一天
    在天（星期）自表达式中，“L”表示一个星期的最后一天，也就是SAT
    如果在“L”前有具体的内容，它就具有其他的含义了
    例如：“6L”表示这个月的倒数第６天，“ＦＲＩＬ”表示这个月的最一个星期五
    注意：在使用“L”参数时，不要指定列表或范围，因为这会导致问题*/
    @Scheduled(cron = "30 34 9 * * ?")

    public void test() {
        System.out.println("corn表达式测试");
    }
}
