package com.wzl.j8new.algorithm;

import com.sun.org.apache.regexp.internal.RE;

import java.util.Arrays;

/**
 * @author wangzhilong
 * @date 2021/6/29 16:48
 * @Description: 解决字符串匹配问题
 */
public class KMP {

    public static void main(String[] args) {
        String str1 = "ABCDAC BDCASDFF";
        String str2 = "WSXX";
        int match = violentMatch(str1, str2);
        System.out.println(match);
        int[] next = kmpNext("ABCDABD");
        System.out.println("next-->" + Arrays.toString(next));
    }

    /**
     *
     * @param str1 源字符串
     * @param str2 子串
     * @param next 子串的部分匹配表
     * @return
     */
    public int kmpSearch(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            while (j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }
    /**
     * 获取字符串（子串）的部分匹配表
     * @param str
     * @return
     */
    private static int[] kmpNext(String str){
        int[] next = new int[str.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < str.length(); i++) {
            while (j > 0 && str.charAt(i) != str.charAt(j)){
                j = next[j -1];
            }
            if (str.charAt(i) == str.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    public static int violentMatch(String str1, String str2){
        int i = 0, j = 0;
        while (i < str1.length()) {
            if (str1.charAt(i) == str2.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
            if (j == str2.length()) {
                return i - j;
            }
        }
        return -1;
    }
}
