package com.wzl.j8new.leetcode;

/**
 * @author wangzhilong
 * @date 2020/7/27 9:56
 * @Description: 最长公共前缀
 */

/*编写一个函数来查找字符串数组中的最长公共前缀。
        如果不存在公共前缀，返回空字符串 ""。
        示例 1:
        输入: ["flower","flow","flight"]
        输出: "fl"
        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/longest-common-prefix*/
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0 || strs == null) {
            return "";
        }
        String prefix = strs[0];
        //先计算两个字符串的公共长度，作为新的字符串与数组下一个元素比较
        for (int i = 1; i < strs.length; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.length() == 0) {
                break;
            }
        }
        return prefix;
    }

    public String longestCommonPrefix(String str1,String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

    //遍历数组中每一个字符串，然后比较相同位置上的字符是否相等
    public String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0 || strs == null) {
            return "";
        }
        int length = strs[0].length(),count = strs.length;
        for (int i = 0; i < length; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < count; j++) {
                if (i == strs[j].length() || c != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    public static void main(String[] args) {
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[]{"asd", "zbc", "asdfgh"}));
    }
}
