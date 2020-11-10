package com.wzl.j8new.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangzhilong
 * @date 2020/11/9 10:20
 * @Description:
 */
public class TestLock {
    public static void main(String[] args) {
        TestLock2 testLock2 = new TestLock2();

        new Thread(testLock2).start();
        new Thread(testLock2).start();
        new Thread(testLock2).start();
    }
}
//TODO 可重入锁优缺点分析
class TestLock2 implements Runnable{

    int tickNum = 10;

    private final ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        try {
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                if (tickNum > 0){
                    System.out.println(tickNum--);
                } else {
                    break;
                }
            }
        } finally {
            lock.unlock();
        }
    }
}
