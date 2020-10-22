package com.wzl.j8new.leetcode;
/*罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
        字符          数值
        I             1
        V             5
        X             10
        L             50
        C             100
        D             500
        M             1000
例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。
数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。
同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
        I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
        X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
        C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
        给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。

来源：力扣（LeetCode）12题
链接：https://leetcode-cn.com/problems/integer-to-roman*/
public class IntToRoman {
    //贪心算法
    public String intToRoman(int num) {
        //定义数字与罗马数字对应的数组,贪心算法降序排列数据
        int[] nums = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romans = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int index = 0;
        StringBuilder sb = new StringBuilder();
        //找出num对应在nums数组中的最大起始位置
        while (index < romans.length){
            //找到起始位置并将罗马数字放进sb，算出新值num，重新参与外层遍历
            while (num >= nums[index]){
                sb.append(romans[index]);
                num -= nums[index];
            }
            index++;
        }
        return sb.toString();
    }

    //贪心算法
    /*public String intToRoman2(int num) {
        //定义数字与罗马数字对应的数组,贪心算法降序排列数据
        int[] nums = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romans = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int index = 0;
        StringBuilder sb = new StringBuilder();
        while (index < nums.length) {
            while (num > nums[index]) {
                sb.append(romans[index]);
                num -= nums[index];
            }
            index++;
        }
        return sb.toString();
    }*/

    public static void main(String[] args) {
        String s = new IntToRoman().intToRoman(1233);
        System.out.println(s);
    }
}
