package com.wzl.j8new.datastructure.sort;

import java.util.Arrays;

/**
 * @author wangzhilong
 * @date 2020/8/14 15:04
 * @Description: 插入排序
 */
public class ChaRu {
    //整体思路是从第二个数据开始，将数组分为两部分。前面作为有序数组，后面作为无序数组
    //依次将无序数组的数插入到有序数组中
    //时间复杂度O(n²)
    public static void main(String[] args) {
        int[] array = {1,-9,8,6,0};
        for (int i = 1; i < array.length; i++) {
            int insertValue = array[i];
            int insertIndex = i - 1;
            while (insertIndex >= 0 && insertValue < array[insertIndex]){
                array[insertIndex + 1] = array[insertIndex];
                insertIndex--;
            }
            array[insertIndex + 1] = insertValue;
            System.out.println("第" + i + "轮后数组" + Arrays.toString(array));
        }
    }
}
