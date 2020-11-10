package com.wzl.j8new.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhilong
 * @date 2020/10/22 9:52
 * @Description: 使用标志位使线程停止
 */
public class TestStop implements Runnable {

    private boolean flag = true;
    @Override
    public void run() {
        int i = 0;
        while (flag){
            System.out.println("Thread:"+ i++);
        }
    }

    public void stop(){
        this.flag = false;
    }

    public static void main(String[] args) {
        TestStop testStop = new TestStop();
        new Thread(testStop).start();

        for (int i = 0; i < 1000; i++) {
            System.out.println("main" + i);
            if (i == 900){
                new TestStop().stop();
                System.out.println("线程停止了");
            }
        }
        new Thread(){//可以重写run方法 也可以使用lambda表达式 如下2处
            @Override
            public void run() {
                super.run();
            }
        }.start();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            int finalI = i;
            new Thread(() ->{//2
                list.add(finalI);
            }).start();
        }
        System.out.println(list.size());
    }
}
