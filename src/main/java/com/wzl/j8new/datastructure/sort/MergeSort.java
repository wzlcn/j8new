package com.wzl.j8new.datastructure.sort;

import java.util.Arrays;

/**
 * @author wangzhilong
 * @date 2020/8/25 21:17
 * @Description: 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 3, 2, 1, 7, 9};
        int temp[] = new int[arr.length];
        mergeSort(arr,0,arr.length - 1,temp);
        System.out.println("归并排序后数组:" + Arrays.toString(arr));
    }

    /**
     * 分解+合并
     * @param arr
     * @param left
     * @param right
     * @param temp
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;//中间索引
            //向左递归进行分解
            mergeSort(arr,left,mid,temp);
            //向右递归进行分解
            mergeSort(arr,mid + 1,right,temp);
//            System.out.println("分解后数组:" + Arrays.toString(arr));
            //合并
            merge(arr,left,mid,right,temp);
        }
    }
    /**
     *  合并的方法
     * @param arr 原始数组
     * @param left 左边有序序列的初始索引
     * @param mid 中间索引
     * @param right 右边有序序列的初始索引
     * @param temp  中转数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;//初始化i，左边有序序列的初始索引
        int j = mid + 1;//初始化j，右边有序序列的初始索引
        int t = 0;//指向temp数组的当前索引
        //(一)
        //先把左右两边(有序)数组按照规则填充到temp中
        //直到左右两边的有序序列，有一边处理完为止
        while (i <= mid && j <= right) {//继续
            //如果左边的有序序列的当前元素小于右边的有序序列的当前元素
            //就将左边的数据填充到temp中，同时t、i都+1
            if (arr[i] < arr[j]){
                temp[t] = arr[i];
                i++;
            } else {//反之将右边数据填充到temp中
                temp[t] = arr[j];
                j++;
            }
            t++;
        }
        //(二)
        //把有剩余数据的有序序列依次填充到temp中去
        while (i <= mid) {//左边有序序列还有剩余数据，全部填充到temp
            temp[t] = arr[i];
            i++;
            t++;
        }
        while (j <= right) {//右边有序序列还有剩余数据，全部填充到temp
            temp[t] = arr[j];
            j++;
            t++;
        }
        //(三)
        //将temp copy到原始数组arr
        t = 0;
        int sortLeft = left;
        while (sortLeft <= right) {
            arr[sortLeft] = temp[t];
            t++;
            sortLeft++;
        }
        System.out.println("排序后数组为:" + Arrays.toString(arr));
    }
}
