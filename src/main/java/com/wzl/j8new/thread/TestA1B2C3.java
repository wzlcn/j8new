package com.wzl.j8new.thread;

/**
 * @author wangzhilong
 * @date 2020/11/16 15:14
 * @Description: 两个线程交替打印A1B2C3....
 */
public class TestA1B2C3 {
    public static void main(String[] args) {
        Object o = new Object();

        new Thread(() ->{
            char alp = 'A';
            try {
                Thread.sleep(100);//确保先打印数字
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o){
                for (int i = 0; i < 26; i++) {
                    try {
                        System.out.println(alp);
                        alp++;
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();//数字打印完之后数字线程处于wait状态，调用notify解除
            }
        },"字母").start();//name写不写都行 此处做下区分

        new Thread(() ->{
            int num = 1;
            synchronized (o){
                for (int i = 0; i < 26; i++) {
                    try {
                        System.out.println(num);
                        num++;
                        o.wait();
                        o.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//                o.notify();
            }
        },"数字").start();
    }
}
