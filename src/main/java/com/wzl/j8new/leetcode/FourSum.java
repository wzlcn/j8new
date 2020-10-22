package com.wzl.j8new.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangzhilong
 * @date 2020/7/29 14:35
 * @Description: 4数之和
 */

/*给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素
        a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
        注意：
        答案中不可以包含重复的四元组。
        示例：
        给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
        满足要求的四元组集合为：
        [
        [-1,  0, 0, 1],
        [-2, -1, 1, 2],
        [-2,  0, 0, 2]
        ]
        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/4sum*/
public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);
        if (nums.length < 4)
            return lists;
        int length = nums.length;
        int c, d;
        //固定a，b两值，然后从c，d范围内取值，注意最外两层跳过重复元素
        for (int a = 0; a <= length -4; a++) {
            if (a > 0 && nums[a] == nums[a - 1])
                continue;
            for (int b = a + 1; b <= length -3; b++) {
                if (b > a + 1 && nums[b] == nums[b - 1])
                    continue;
                c = b + 1;
                d = length - 1;
                while (c < d) {
                    if (nums[a] + nums[b] + nums[c] + nums[d] < target)
                        c++;
                    else if (nums[a] + nums[b] + nums[c] + nums[d] > target)
                        d--;
                    else{
                        lists.add(Arrays.asList(nums[a],nums[b],nums[c],nums[d]));
                        //跳到c最后一个重复元素，仔细品一下
                        //当走此步时，下面c++刚好跳过重复元素
                        while (c < d && nums[c] == nums[c + 1])
                            c++;
                        //跳到d最后一个重复元素
                        while (c < d && nums[d] == nums[d - 1])
                            d--;
                        c++;
                        d--;
                    }
                }
            }
        }
        return lists;
    }

    public static void main(String[] args) {
        System.out.println(new FourSum().fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
    }
}
