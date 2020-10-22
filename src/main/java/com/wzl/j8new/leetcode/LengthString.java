package com.wzl.j8new.leetcode;

import java.util.HashSet;
import java.util.Set;
/*给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。*/
public class LengthString {
    public static int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set set = new HashSet();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, length = 0;
        // 字符串长度
        int n = s.length();
        for (int i = 0; i < n; i++) {
            // 左指针向右移动一格，移除一个字符
            if (i != 0) {
                set.remove(s.charAt(i-1));
            }
            while (rk + 1 < n && !set.contains(s.charAt(rk +1))){
                // 不断地移动右指针
                set.add(s.charAt(rk +1));
                ++rk;
            }
            // 取最大值
            length = Math.max(length, rk - i + 1);
        }
        return length;
    }

    //定义左右指针，遍历字符串并加入一个set集合，如果字符不重复右指针不断右移，如果出现
    //重复字符，左指针右移一位并移除这个字符，并找出最大值
    /*public static int lengthOfLongestSubstring2(String s) {
        Set set = new HashSet();
        int rk = -1,length = 0;
        for (int i = 0; i < s.toCharArray().length; i++) {
            if (i != 0) {
                set.remove(s.charAt(i - 1));
            }
            if (rk + 1 < s.toCharArray().length && !set.contains(s.charAt(rk + 1))) {
                set.add(s.charAt(rk + 1));
                ++rk;
            }
            length = Math.max(length, rk + 1 - i);
        }
        return length;
    }*/

    public static void main(String[] args) {
        String s = "pwwkew";
        int i = lengthOfLongestSubstring(s);
        System.out.println(i);
    }
}
