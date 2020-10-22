package com.wzl.j8new.bean;


import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private String userName;
    private String age;

    public User(){};
    public User(String userName, String age) {
        this.userName = userName;
        this.age = age;
    }



    public String getFirstAlphabet(){
        return this.userName.substring(0,1);
    }
}
