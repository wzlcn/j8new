package com.wzl.j8new.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author wzlong
 * @Date 2023/6/20 14:39
 * @Description:
 */
public class Test123InOrder {
    //synchronized实现
    /*public static void main(String[] args) {
        wait wait = new wait(1, 5);
        new Thread(() -> wait.print("a", 1, 2)).start();
        new Thread(() -> wait.print("b", 2, 3)).start();
        new Thread(() -> wait.print("c", 3, 1)).start();
    }*/
    /*//ReentrantLock、Condition实现
    public static void main(String[] args) {
        Wait2 wait2 = new Wait2(5);
        Condition c1 = wait2.newCondition();
        Condition c2 = wait2.newCondition();
        Condition c3 = wait2.newCondition();
        new Thread(() -> wait2.print("a",c1,c2)).start();
        new Thread(() -> wait2.print("b",c2,c3)).start();
        new Thread(() -> wait2.print("c",c3,c1)).start();
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait2.lock();
        try {
            System.out.println("开始");
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            wait2.unlock();
        }
    }*/
}

class WaitChange {

    private int flag;

    private int loopNumber;

    public WaitChange(int flag, int loopNumber) {
        this.flag = flag;
        this.loopNumber = loopNumber;
    }
    public void print(String str,int waitFlag,int nextFlag){
        for (int i = 0; i < loopNumber; i++) {
            synchronized (this){
                while(flag != waitFlag){
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(str+" ");
                flag = nextFlag;
                this.notifyAll();
            }
        }
    }
}

class Wait2 extends ReentrantLock{

    private int loopNum;

    public Wait2(int loopNum) {
        this.loopNum = loopNum;
    }

    public void print(String s, Condition curCon, Condition nextCon) {
        for (int i = 0; i < loopNum; i++) {
            lock();
            try {
                curCon.await();
                System.out.print(s + " ");
                nextCon.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                unlock();
            }
        }
    }
}