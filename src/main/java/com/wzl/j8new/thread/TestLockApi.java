package com.wzl.j8new.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author wzlong
 * @Date 2021/9/2 14:43
 * @Description:
 */
class ShareData {
    private int num = 0;
    private Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();
        try {
            while (num != 0) {
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            condition.signalAll();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        try {
            while (num == 0) {
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            condition.signalAll();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }

}

public class TestLockApi{
    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(() ->{
            for (int i = 0; i < 5; i++) {
                shareData.increment();
            }
        },"AA").start();
        new Thread(() ->{
            for (int i = 0; i < 5; i++) {
                shareData.decrement();
            }
        },"BB").start();
    }
}
