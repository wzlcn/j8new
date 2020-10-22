package com.wzl.j8new.datastructure.sort;

import java.util.Arrays;

/**
 * @author wangzhilong
 * @date 2020/8/12 13:50
 * @Description: 冒泡排序之优化写法
 */
public class MaoPao {
    public static void main(String[] args) {
        int temp = 0;
//        int[] array = {-2,-10,9,22,3};
        int[] array = {1,2,3,4,5};
        boolean flag = false;//标识是否有元素进行交换
        //时间复杂度为O(n²)，因为是两个for循环
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]){
                    flag = true;
                    temp = array[j];
                    array[j]  = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            System.out.println("第" + (i + 1) + "趟排序后的数组：" + Arrays.toString(array));
            if (!flag){//某趟排序后发现没有元素进行交换，则直接结束外层循环
                break;
            } else {//如果有交换则重置flag，留待下趟判断
                flag = false;
            }
        }
    }
}
