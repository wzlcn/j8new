package com.wzl.j8new.leetcode;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author wangzhilong
 * @date 2020/8/3 9:52
 * @Description:
 */

/*给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
        有效字符串需满足：
        左括号必须用相同类型的右括号闭合。
        左括号必须以正确的顺序闭合。
        注意空字符串可被认为是有效字符串。
        示例 1:
        输入: "()"
        输出: true
        示例 2:
        输入: "()[]{}"
        输出: true
        示例 3:
        输入: "(]"
        输出: false
        示例 4:
        输入: "([)]"
        输出: false
        示例 5:
        输入: "{[]}"
        输出: true
        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/valid-parentheses*/
public class IsValidString {
    private HashMap<Character,Character> mappings;
    public IsValidString(){
        this.mappings = new HashMap<>();
        this.mappings.put('}', '{');
        this.mappings.put(']', '[');
        this.mappings.put(')', '(');
    }
    //如果一个字符串按照规则时有效的，那么内层的子串必定也是有效的
    //我们可以从内层开始，消除掉匹配的字符，重复该步骤，最后看剩余的字符串是否为空
    //若为空则符合题意(开括号和闭括号数目是相同的)，不为空则不符合题意
    //而从内层开始消除，栈这种LIFO(LAST IN FIRST OUT)后进先出的数据结构刚好适用此场景
    public boolean isValid(String s){
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (mappings.containsKey(c)) {
                //若栈不为空，获取到栈顶元素
                char topElement = stack.isEmpty() ? '#' : stack.pop();
                //栈顶元素与当前遍历元素看是否满足映射关系
                if (topElement != mappings.get(c)) {
                    return false;
                }
            } else {
                //若mappings不包含c，将元素放入栈顶
                stack.push(c);
            }
        }
        //最终判断栈内是否还有元素
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new IsValidString().isValid("()[]"));
    }
}
