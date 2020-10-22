package com.wzl.j8new.leetcode;
/*给定一个字符串 s，找到 s 中最长的回文子串。*/
//中心扩展算法
public class  LongestPalindrome {


    public String longestPalindrome(String s) {
        if(s == null || s.length() < 1)
            return "";
        int start = 0,end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1)/2;
                end  = i + len/2;
            }
        }
        return s.substring(start, end + 1);
    }
    //如果left=right，则以当前字符为中心向两边扩展
    //如果left=right-1，则以这两个字符为中心向外扩展。字符的index即为left和right的值。
    private int expandAroundCenter(String s,int left,int right){
        int L = left,R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)){
            L--;
            R++;
        }
        return R - L - 1;
    }

    public static void main(String[] args) {
        LongestPalindrome ll = new LongestPalindrome();
        String s = ll.longestPalindrome("ssdasddsabb");
        System.out.println(s);
    }
}
