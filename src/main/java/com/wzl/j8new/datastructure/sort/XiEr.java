package com.wzl.j8new.datastructure.sort;

import java.util.Arrays;

/**
 * @author wangzhilong
 * @date 2020/8/14 16:29
 * @Description: 希尔排序--交换发
 */
public class XiEr {
    public static void main(String[] args) {
        //交换式----效率低
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 0};
        int temp = 0;
        int count = 0;
        //确定步长及轮数
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            //遍历按步长gap分组后的每一组元素
            for (int i = gap; i < array.length; i++) {
                //按顺序遍历一组中的元素
                for (int j = i - gap; j >= 0; j -= gap) {
                    //这里其实是插入排序
                    if (array[j] > array[j + gap]){
                        temp = array[j];
                        array[j] =  array[j + gap];
                        array[j + gap] = temp;
                    }
                }
            }
            System.out.println("第" + (++count) + "轮后数组为" + Arrays.toString(array));
        }
//        shellSort2(array);
    }

    //移位法
    public static void shellSort2(int[] array){
        //增量gap,每次缩小gap
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                int j = i;
                int temp = array[j];
                //相当于插入排序
                if (array[j] < array[j - gap]){
                    while (j - gap >= 0 && temp < array[j - gap]){
                        array[j] = array[j - gap];
                        j -= gap;
                    }
                    //退出循环后，给temp找到插入的位置
                    array[j] = temp;
                }
            }
        }
        System.out.println("排序后数组为" + Arrays.toString(array));
    }
}
