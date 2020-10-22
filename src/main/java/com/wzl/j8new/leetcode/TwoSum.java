package com.wzl.j8new.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
/*给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
来源：力扣（LeetCode）*/
public class TwoSum {
    //时间复杂度O(n),只需遍历一遍数组
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < nums.length; i++) {
            int another = target - nums[i];
            if (map.containsKey(another)) {
                long end = System.currentTimeMillis();
                long time = end - start;
                System.out.println("twoSum耗时-------" + time);
                return new int[]{map.get(another), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("没有这样的两个数");
    }

    //两遍遍历，时间复杂度O(n²)
    public int[] twoSum2(int[] nums, int target) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    long end = System.currentTimeMillis();
                    long time = end - start;
                    System.out.println("twoSum2耗时-------" + time);
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("没有这样的两个数");
    }

    /*public int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int another = target - nums[i];
            if (map.containsKey(another)) {
                return new int[]{map.get(nums[i]), i};
            }
            map.put(nums[i], i);
        }
        throw  new IllegalArgumentException("没有这样的两个数");
    }*/

    public static void main(String[] args) {
        int[] a = {2, 7, 8, 9, 10};
        int target = 9;
        TwoSum twoSum = new TwoSum();
        int[] ints = twoSum.twoSum(a, target);
        System.out.println(Arrays.toString(ints));
        int[] ints2 = twoSum.twoSum2(a, target);
        System.out.println(Arrays.toString(ints2));

    }
}
