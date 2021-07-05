package com.wzl.j8new.algorithm;

/**
 * @author wangzhilong
 * @date 2021/6/24 9:45
 * @Description: 利用分治算法解决汉诺塔问题
 */
public class Hanoitower {
    public static void main(String[] args) {
        hanoiTower(3, 'A', 'B', 'C');
    }

    /**
     * @param num 汉诺塔层数
     * @param a   承载最初圆盘的柱子
     * @param b   起到中转作用的柱子
     * @param c   移动到的目标柱子
     */
    public static void hanoiTower(int num, char a, char b, char c) {
        //如果只有一个盘
        if (num == 1) { //递归终止条件
            System.out.println("第1个盘从 " + a + "->" + c);
        } else { //num >=2
            //即num>=2的情况:我们可以将若干个盘(n>=2)一步步地分解:都可以看做是两个盘的情况
            //(1)先将上面的n-1个盘从A移动到B:      A->B
            hanoiTower(num - 1, a, c, b);  //借助C将num-1个盘从A移动到B
            //(2)再将最下面的盘从A移动到C:         A->C
            System.out.println("第" + num + "个盘从 " + a + "->" + c);
            //(3)最后将上面的n-1个盘从B移动到C:    B->C
            hanoiTower(num - 1, b, a, c);   //借助A将num-1个盘从B移动到C
        }
    }
}
