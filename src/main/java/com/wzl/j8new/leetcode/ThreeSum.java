package com.wzl.j8new.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangzhilong
 * @date 2020/7/27 14:01
 * @Description: 三数之和为0
 */

/*给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？
        请你找出所有满足条件且不重复的三元组。
        注意：答案中不可以包含重复的三元组。
        示例：
        给定数组 nums = [-1, 0, 1, 2, -1, -4]，
        满足要求的三元组集合为：
        [
        [-1, 0, 1],
        [-1, -1, 2]
        ]
        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/3sum*/
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        //将数组元素从小到大排序
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        //枚举a
        for (int first = 0; first < n; first++) {
            //为了避免返回的数组包含相同元素的重复数组，每层循环跳过相同元素
            if (first > 0 && nums[first] == nums[first - 1]){
                continue;
            }
            //c 对应的初始指针指向数组的最右边
            int third = n - 1;
            //枚举b
            for (int second = first + 1; second < n; second++) {
                if (second > first + 1 && nums[second] == nums[second - 1]){
                    continue;
                }
                //当不满足a+b+c=0时将c的指针左移一位
                while (second < third && nums[first] + nums[second] + nums[third] > 0){
                    third--;
                }
                // 因为我们整体要满足a≤b≤c
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third){
                    break;
                }
                if (nums[first] + nums[second] + nums[third] == 0){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new ThreeSum().threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }
}
