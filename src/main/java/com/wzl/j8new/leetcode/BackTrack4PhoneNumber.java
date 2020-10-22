package com.wzl.j8new.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangzhilong
 * @date 2020/7/29 10:36
 * @Description: 回溯计算电话号码与字母的组合情况
 */

/*给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
        给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
        示例:
        输入："23"
        输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number*/
public class BackTrack4PhoneNumber {
    Map<String,String> map = new HashMap<String, String>(){{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};
    List<String> output = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits.length() != 0)
            backtrack("", digits);
        return output;
    }

    public void backtrack(String combination, String next_digits) {
        //当没有数字时，已经到达最底层，将该组合放入集合中
        if (next_digits.length() == 0) {
            output.add(combination);
        } else {
            //获取到第一个数字
            String digit = next_digits.substring(0, 1);
            //从map中获取该数字对应的字符串
            String letters = map.get(digit);
            //遍历该字符串
            for (int i = 0; i < letters.length(); i++) {
                //取到该字符串的第一个字母，加到传入的combination上，继续调用该方法
                //直到没有数字输入，产生一个组合
                String letter = letters.substring(i, i + 1);
                backtrack(combination + letter,next_digits.substring(1));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new BackTrack4PhoneNumber().letterCombinations("369"));
    }
}
