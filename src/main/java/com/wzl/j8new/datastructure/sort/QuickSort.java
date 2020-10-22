package com.wzl.j8new.datastructure.sort;

import java.util.Arrays;

/**
 * @author wangzhilong
 * @date 2020/8/24 21:37
 * @Description: 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9,78,0,23,-567,70};
        quickSort(arr,0,arr.length - 1);
    }

    public static void quickSort(int[] arr, int left, int right){
        int l = left;//左下标
        int r = right;//右下标
        int temp = 0;
        //pivot 中轴
        int pivot = arr[(left + right) / 2];
        //while循环是让比pivot小的值放在左边，比pivot大的值放在它的右边
        while (l < r){
            //在pivot左边一直找，直到找到大于等于pivot的值
            while (arr[l] < pivot){
                l++;
            }
            //在pivot右边一直找，直到找到小于等于pivot的值
            while (arr[r] > pivot){
                r--;
            }
            //如果出现该情况，已经满足pivot左边全是小于pivot的值，右边全是大于pivot的值
            if (l >= r){
                break;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //前移
            if (arr[l] == pivot){
                r--;
            }
            //后移
            if (arr[r] == pivot){
                l++;
            }
        }
//        System.out.println("数组为：" + Arrays.toString(arr));
        if (l == r){
            l++;
            r--;
        }
        //向左递归
        if (left < r){
            quickSort(arr,left,r);
        }
        //向右递归
        if (right > l){
            quickSort(arr,l,right);
        }
        System.out.println("数组为：" + Arrays.toString(arr));
    }
}
