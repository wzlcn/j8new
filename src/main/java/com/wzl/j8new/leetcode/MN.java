package com.wzl.j8new.leetcode;

/**
 * @author wangzhilong
 * @date 2020/7/24 17:11
 * @Description: 步数问题：有n阶台阶，每次走一阶或者二阶，有多少种走法？
 */
public class MN {
    public  long getStepNumber(int n)  {
        if (0 > n) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (n > 2) {
            return getStepNumber(n - 1) + getStepNumber(n - 2);
        }
        return 0;
    }
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println("开始时间--------------" + start);
        System.out.println(new MN().getStepNumber(4));
        long end = System.currentTimeMillis();
        System.out.println("结束时间--------------" + end);
        System.out.println("耗时--------------" + (end - start));
    }
}
