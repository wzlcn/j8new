package com.wzl.j8new.leetcode;

import java.util.ArrayList;
import java.util.List;

/*将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
        L   C   I   R
        E T O E S I I G
        E   D   H   N
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。*/
public class ConvertStringAsZ {
    public String convert(String s,int numRows){
        if (numRows == 1)
            return s;
        List<StringBuffer> rows = new ArrayList<>();
        for (int i = 0;i < Math.min(s.length(),numRows);i++){
            rows.add(new StringBuffer());
        }
        //定义当前行
        int curRow = 0;
        //定义当前方向
        boolean goingDown = false;
        for (char c : s.toCharArray()) {
            //将当前字符添加进当前行
            rows.get(curRow).append(c);
            //只有在第一行和最后一行时才转换方向
            if (curRow == 0 || curRow == numRows - 1)
                goingDown = !goingDown;
            //行数加1或者减1
            curRow += goingDown ? 1 : -1;
        }
        StringBuilder sb = new StringBuilder();
        for (StringBuffer row : rows) {
            sb.append(row);
        }
        return sb.toString();
    }

    /*public String convert2(String s,int numRows){
        if (numRows == 1)
            return s;
        List<StringBuffer> rows = new ArrayList<>();
        for (int i = 0;i < Math.min(s.length(),numRows);i++){
            rows.add(new StringBuffer());
        }
        int curRow = 0;
        boolean goingDown = false;
        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1)
                goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }
        StringBuilder sb = new StringBuilder();
        for (StringBuffer row : rows) {
            sb.append(row);
        }
        return sb.toString();
    }*/

    public static void main(String[] args) {
        ConvertStringAsZ cc = new ConvertStringAsZ();
        String convert = cc.convert("asdfghjkl", 3);
        System.out.println(convert);
    }
}
