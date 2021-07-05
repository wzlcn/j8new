package com.wzl.j8new.thread;

/**
 * @author wangzhilong
 * @date 2020/11/10 17:27
 * @Description:
 */
public class TestVolatile {
    //volatile保证线程之间可见性-->运用MESI(缓存一致性协议)缓存行--->缓存解决不了 就锁总线
    /*volatile*/ boolean running = true;
    void m(){
        System.out.println("m start");
        while (running){
        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        TestVolatile testVolatile = new TestVolatile();
        new Thread(testVolatile::m,"t1").start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testVolatile.running = false;
    }
}
