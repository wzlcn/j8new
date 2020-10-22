package com.wzl.j8new.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarTest {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        Date now = new Date();
        int date = calendar.get(Calendar.DATE);
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR,calendar.get(Calendar.DAY_OF_YEAR) - 15);
//        String format = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.DAY_OF_YEAR);
        System.out.println(date);
        System.out.println(dayOfYear);
//        System.out.println(format);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
    }
}
