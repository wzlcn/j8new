package com.wzl.j8new.leetcode;
/*判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数*/
public class IsPalindrome {
    //将这个数字反转，与原数相等就是回文数
    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        if (x == 0)
            return true;
        int pre = x;
        int rev = 0;
        while (x != 0){
            int pop = x % 10;
            x /= 10;
            rev = rev * 10 + pop;
        }
        return pre == rev ? true : false;
    }

    public static void main(String[] args) {
        IsPalindrome isPalindrome = new IsPalindrome();
        boolean palindrome = isPalindrome.isPalindrome(0);
        System.out.println(palindrome);
    }
}
