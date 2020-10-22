package com.wzl.j8new.collection;

import org.apache.commons.collections.MapUtils;

import java.util.HashMap;
import java.util.Map;

public class UtilsTest {

    public static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
    public static void main(String[] args) {
        /*Map<String, Object> map = new HashMap<>();
        map.put("1",1);
        map.put("2",2);
        map.put("3",3);
        map.put("4",4);

        String key1 = MapUtils.getString(map, "1"),
        key2 = MapUtils.getString(map, "2");
        System.out.printf(key1 + " " + key2);*/

        /*boolean b = false;
        int a = 0;
        a += b ? 1 : -1;
        System.out.println(a);*/
        int a = UtilsTest.expandAroundCenter("aba", 1, 1);
        System.out.println(a);
    }
}
