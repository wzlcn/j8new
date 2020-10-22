package com.wzl.j8new.datastructure.sort;

import java.util.Arrays;

/**
 * @author wangzhilong
 * @date 2020/8/26 16:13
 * @Description: 基数排序 radix--基数
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
//        radixSort(arr);
        radix2(arr);
    }

    public static void radixSort(int[] arr){
        //找出数组中最大数的位数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();
        //定义一个二维数组，表示10个桶，每个桶都是一个一维数组
        //空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];
        //定义一个一维数组，用来计算各个桶中放入元素的个数
        //比如bucketElementCount[0]记录的就是bucket[0]桶中元素的个数
        int[] bucketElementCount = new int[10];
        for (int i = 0,n = 1; i < maxLength; i++, n *= 10) {
            //第一轮(针对每个元素的各位进行排序)第一轮取个位，第二轮取十位，第三轮取百位……
            for (int j = 0; j < arr.length; j++) {
                //取出对应位的值
                int digitElement = arr[j] / n % 10;
                //确定这个数放入哪个桶中
                bucket[digitElement][bucketElementCount[digitElement]] = arr[j];
                //一个桶放入一个元素，计数就+1
                bucketElementCount[digitElement]++;
            }
            //按照桶的顺序(一维数组的下标依次取出数据，放入原数组)
            int index = 0;
            //遍历每个桶，将桶中的数据放入原数组
            for (int k = 0; k < bucket.length; k++) {
                //如果桶中有数据，才取出放入到原数组中
                if (bucketElementCount[k] != 0){
                    //循环该桶，其实就是个一维数组
                    for (int l = 0; l < bucketElementCount[k]; l++) {
                        arr[index] = bucket[k][l];
                        index++;
                    }
                }
                //每一轮过后都将桶中计数清除
                bucketElementCount[k] = 0;
            }
            System.out.println("第" + (i + 1) + "轮后的数组:" + Arrays.toString(arr));
        }
    }

    public static void radix2(int[] arr){
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();

        int[][] bucket = new int[10][arr.length];
        int[] bucketElementCount = new int[10];
        for (int i = 0,n = 1; i < maxLength; i++,n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                int digitElement = arr[j] / n % 10;
                bucket[digitElement][bucketElementCount[digitElement]] = arr[j];
                bucketElementCount[digitElement]++;
            }
            int index = 0;
            for (int k = 0; k < bucket.length; k++) {
                if (bucketElementCount[k] != 0){
                    for (int l = 0; l < bucketElementCount[k]; l++) {
                        arr[index] = bucket[k][l];
                        index++;
                    }
                }
                bucketElementCount[k] = 0;
            }
            System.out.println("第" + (i + 1) + "轮排序后数组:" + Arrays.toString(arr));
        }
    }
}
