package com.wzl.j8new.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wzl.j8new.bean.User;


import java.util.Arrays;
import java.util.List;

public class ObjectMapperTest {
    public static void main(String[] args) {
        String s = "{\"userName\": \"张三\",\"age\": \"20\"}";
        try {
            User user = new ObjectMapper().readValue(s, User.class);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int a[] = {1,2,3,4,5};
        for (int i = 0; i < a.length; i++) {
            List<int[]> ints = Arrays.asList(a);

        }
    }


}
