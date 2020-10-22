package com.wzl.j8new.datastructure.sort;

import java.util.Arrays;

/**
 * @author wangzhilong
 * @date 2020/8/13 13:21
 * @Description: 选择排序
 */
public class XuanZe {
    public static void main(String[] args) {
        int[] array = {-9,100,0,-1,2};
        //两层for循环，时间复杂度O(n²)
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            int min = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (min > array[j]){
                    min = array[j];//重置min
                    minIndex = j;//重置minIndex
                }
            }
            if (minIndex != i){//将找到的最小值与每一轮的array[0]交换
                array[minIndex] = array[i];
                array[i] = min;
            }
            System.out.println("第"+(i + 1)+"趟排序后数组为：" + Arrays.toString(array));
        }
    }
}
