package com.wzl.j8new.bean;

import lombok.Data;

/**
 * @author wangzhilong
 * @date 2020/10/30 15:42
 * @Description:
 */
@Data
public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String toString() {
        return name;
    }

    public enum Type {MEAT, FISH, OTHER}
}
