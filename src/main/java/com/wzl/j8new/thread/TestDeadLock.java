package com.wzl.j8new.thread;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author wzlong
 * @Date 2021/9/6 17:14
 * @Description:
 */
class DeadLock implements Runnable{
    private String lockA;
    private String lockB;

    public DeadLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "拥有" + lockA + "尝试获取" + lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "拥有" + lockB + "尝试获取" + lockA);
            }
        }
    }
}
public class TestDeadLock {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new DeadLock(lockB,lockA),"AAAA").start();
        new Thread(new DeadLock(lockA,lockB),"BBBB").start();
        long l = Runtime.getRuntime().totalMemory();
        long l1 = Runtime.getRuntime().maxMemory();
    }
}
