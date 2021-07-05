package com.wzl.j8new.algorithm;

/**
 * @author wangzhilong
 * @date 2021/6/23 16:23
 * @Description: 二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 8, 9, 100};
//        int index = binarySearch(arr, 8,0,arr.length - 1);
        int index = binarySearch2(arr, 8,0,arr.length - 1);
        System.out.println("index ->" + index);
    }

    /**
     * 非递归二分查找
     *
     * @param arr    原始数组 默认升序
     * @param target 需要查找的目标元素
     */
    public static int binarySearch(int[] arr, int target, int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target == arr[mid]) {
                return mid;
            } else if (target < arr[mid]) {  //向左查找
                right = mid - 1;
            } else {    //向右查找 target > arr[mid]
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找递归写法
     *
     * @param arr
     * @param target
     * @param left
     * @param right
     * @return
     */
    public static int binarySearch2(int[] arr, int target, int left, int right) {
        if (left > right)
            return -1;
        int mid = (left + right) / 2;
        if (target == arr[mid]) {
            return mid;
        } else if (target < arr[mid]) {  //向左查找
            return binarySearch2(arr, target, left, mid - 1);
        } else {    //向右查找 target > arr[mid]
            return binarySearch2(arr, target, mid + 1, right);
        }
    }
}
