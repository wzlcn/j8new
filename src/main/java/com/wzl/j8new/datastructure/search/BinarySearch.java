package com.wzl.j8new.datastructure.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author wangzhilong
 * @date 2020/9/14 14:29
 * @Description: 二分查找 数组必须是有序的
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1,123,123,123,123,124,999};
//        int index = binarySearch(arr, 0, 5, 0);
        List<Integer> list = binarySearch2(arr, 0, 6, 123);
        System.out.println(list);
    }

    /**
     * 二分查找
     * @param arr 查找数组
     * @param left 左索引
     * @param right 右索引
     * @param findVal 找到返回下标 找不到返回-1
     * @return
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal){
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (left > right){
            return -1;
        }
        if (midVal > findVal){//向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else if (midVal < findVal){//向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else {
            return mid;
        }
    }


    /**
     * 若数组中有重复值，返回所有下标
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal){
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (left > right){
            return new ArrayList<>();
        }
        if (midVal > findVal){//向左递归
            return binarySearch2(arr, left, mid - 1, findVal);
        } else if (midVal < findVal){//向右递归
            return binarySearch2(arr, mid + 1, right, findVal);
        } else {
            List<Integer> resIndexList = new ArrayList<>();
            int temp = mid - 1;
            while (true) {//向左扫描寻找等于findVal的值
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                resIndexList.add(temp);
                temp -= 1;
            }
            resIndexList.add(mid);
            temp = mid + 1;
            while (true) {//向右扫描寻找等于findVal的值
                if (temp > arr.length - 1 || arr[temp] != findVal) {
                    break;
                }
                resIndexList.add(temp);
                temp += 1;
            }
            Collections.sort(resIndexList);//排序返回
            return resIndexList;
        }
    }
}
