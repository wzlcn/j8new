package com.wzl.j8new.leetcode;

import java.util.Arrays;

/**
 * @author wangzhilong
 * @date 2020/7/29 9:59
 * @Description: 最接近的三数之和
 */

/*给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。
返回这三个数的和。假定每组输入只存在唯一答案。
        示例：
        输入：nums = [-1,2,1,-4], target = 1
        输出：2
        解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/3sum-closest*/
public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        //先将数组从小到大排序
        Arrays.sort(nums);
        //初始化ans，用来与target比较
        int ans = nums[0] + nums[1] + nums[2];
        int n = nums.length;
        //第一层循环，固定当前值，然后从后面的值的范围内取值：即start与end范围
        for (int i = 0; i < n; i++) {
            //定义前后指针
            int start = i + 1,end = n - 1;
            //第二层循环，因为数组已排序，必须满足start < end
            while (start < end) {
                //计算当前三个下标对应值的和
                int sum = nums[i] + nums[start] + nums[end];
                //计算初始化ans与新值sum距target的距离。如果sum距离target更近，就替换ans
                if (Math.abs(sum - target) < Math.abs(ans - target)) {
                    ans = sum;
                }
                //同时比较sum与target的大小，如果sum < target，start++
                //∵a+b+c-->target，a值在第一层循环已固定，b值对应下标为start，c值对应下标为end
                //在a+b+c的和sum < target时，如果想让sum更接近target，那么只能b↑，即向右挪动b的指针
                if (sum < target) {
                    start++;
                }
                //在a+b+c的和sum > target时，如果想让sum更接近target，那么只能c↓，即向左挪动c的指针
                if (sum > target) {
                    end--;
                }
                //在a+b+c的和sum = target时，直接返回三数之和
                if (sum == target) {
                    return ans;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new ThreeSumClosest().threeSumClosest(new int[]{-1, 2, 1, -4}, 2));
    }
}
