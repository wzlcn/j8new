package com.wzl.j8new.datastructure.search;

/**
 * @author wangzhilong
 * @date 2020/8/28 10:08
 * @Description: 线性查找
 */
public class SequenceSearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, -1, 9, 255};
        seqSearch(arr, -1);
    }

    /**
     * 只找到第一个，如果多个 可以把下标放进集合返回
     * @param arr
     * @param target
     * @return
     */
    public static int seqSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                System.out.println(i);
                return i;
            }
        }
        return -1;
    }
}
