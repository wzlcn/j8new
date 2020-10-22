package com.wzl.j8new.thread;

/**
 * @author wangzhilong
 * @date 2020/10/16 16:06
 * @Description:
 */
public class TestThread1 implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() +  "的线程启动");
    }

    public static void main(String[] args) {
        TestThread1 t1 = new TestThread1();
        new Thread(t1,"小王").start();
        new Thread(t1,"小李").start();
        new Thread(t1,"小赵").start();
    }
}
