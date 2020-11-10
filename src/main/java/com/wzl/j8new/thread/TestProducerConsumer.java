package com.wzl.j8new.thread;

/**
 * @author wangzhilong
 * @date 2020/11/9 11:01
 * @Description: 线程之间生产者消费者模式
 */
//利用缓冲区进行线程间通信 管程法
//不知道为什么会有问题
public class TestProducerConsumer {
    public static void main(String[] args) {
        SynContainer synContainer = new SynContainer();

        new Producer(synContainer).start();
        new Consumer(synContainer).start();
    }
}

class Producer extends Thread{

    SynContainer synContainer;

    public Producer(SynContainer synContainer){
        this.synContainer = synContainer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            synContainer.push(new Chicken(i));
            System.out.println("生产了第" + i + "只鸡");
        }
    }
}

class Consumer extends Thread{

    SynContainer synContainer;

    public Consumer(SynContainer synContainer){
        this.synContainer = synContainer;
    }
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("消费了第" + synContainer.pop().id + "只鸡");
        }
    }
}

class Chicken{
    int id;

    public Chicken(int id) {
        this.id = id;
    }
}

class SynContainer{

    volatile Chicken[] chickens = new Chicken[10];
    volatile int count = 0;


    public synchronized void push(Chicken chicken){
        while (count == chickens.length){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        chickens[count] = chicken;
        count++;
        this.notifyAll();
    }

    public synchronized Chicken pop(){
        while (count == 0){
            try {
                this.wait();
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        count--;
        Chicken chicken = chickens[count];
        this.notifyAll();
        return chicken;
    }
}
