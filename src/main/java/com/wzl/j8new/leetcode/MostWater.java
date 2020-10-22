package com.wzl.j8new.leetcode;
/*给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
        在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
        找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
        说明：你不能倾斜容器，且 n 的值至少为 2。
        图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
LeetCode第11题*/
public class MostWater {
    public int maxArea(int[] height) {
        //定义左右两个指针
        int l = 0, r = height.length - 1, ans = 0;
        while (l < r){
            //计算容量
            int maxArea = Math.min(height[l] , height[r]) * (r - l);
            //取最大值
            ans = Math.max(maxArea, ans);
            //左指针对应的数小，则将左指针右移一位
            if (height[l] <= height[r]) {
                ++l;
            }
            //右指针对应的数小，则将右指针左移一位
            else {
                --r;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        MostWater mostWater = new MostWater();
        int maxArea = mostWater.maxArea(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        System.out.println(maxArea);
    }
}
