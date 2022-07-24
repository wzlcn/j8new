package com.wzl.j8new.test;

import lombok.Synchronized;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class HelloWorld {

    static final Lock lock1=new ReentrantLock();
    static final Lock lock2=new ReentrantLock();

    static Object a = new Object();


    public  void test1(){
        int i=0;
        while (true) {
            lock1.lock();
            lock2.lock();

            System.out.println("Thread1"+i++);
            lock2.unlock();
            lock1.unlock();

        }
    }

    public void test2(){
        int i=0;
        while (true) {  //死循环目的是为了让线程一直持有该锁
            lock2.lock();
            lock1.lock();

            System.out.println("Thread2"+i++);
            lock1.unlock();
            lock2.unlock();
        }
    }

    public static void main(String[] args) {
//        final HelloWorld test=new HelloWorld();
//
//        new Thread(new  Runnable() {
//            public void run() {
//                test.test2();
//            }
//        }).start();
//        new Thread(new  Runnable() {
//            public void run() {
//                test.test1();
//            }
//        }).start();
        /*synchronized(a){
            synchronized(a){

            }
        }*/

        String idCard = "4103291992032425233";
        String name = "上官飞燕";
        String encryptName = "";
        String encryptIdCard = idCard.substring(0,6) + "********" + idCard.substring(14,18);
        if (name.length() == 3) {
            encryptName = name.substring(0,1) + "**";
        } else if (name.length() == 2){
            encryptName = name.substring(0,1) + "*";
        } else {
            encryptName = name.substring(0,2) + "**";
        }
        System.out.println(encryptIdCard);
        System.out.println(encryptName);
    }

}