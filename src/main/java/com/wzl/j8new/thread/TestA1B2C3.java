package com.wzl.j8new.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
                        System.out.print(alp);
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
                        System.out.print(num);
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

class print1A2B{
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() ->{
            char c = 'A';
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            for (int i = 0; i < 26; i++) {
                System.out.print(c);
                c++;
                condition.signalAll();
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            condition.signalAll();
            lock.unlock();
        },"字母").start();

        new Thread(() ->{
            int num = 1;
            lock.lock();
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 26; i++) {
                System.out.print(num);
                num++;
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                condition.signalAll();
            }
            lock.unlock();
        },"数字").start();
    }
}