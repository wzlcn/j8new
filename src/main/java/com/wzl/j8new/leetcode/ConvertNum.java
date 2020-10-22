package com.wzl.j8new.leetcode;
/*给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。*/
public class ConvertNum {
    public int convertNum(int num){
        int rev = 0;
        while (num != 0){
            int pop = num % 10;
            num /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7))
                return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8))
                return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    /*public int convertNum2(int num){
        int convert = 0;
        while (num != 0) {
            int pop = num % 10;
            num /= 10;
            convert = convert * 10 + pop;
        }

        return convert;
    }*/

    public static void main(String[] args) {
        ConvertNum convertNum = new ConvertNum();
        int i = convertNum.convertNum(-301);
        System.out.println(i);
    }
}