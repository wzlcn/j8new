package com.wzl.j8new.thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author wzlong
 * @Date 2022/8/29 17:11
 * @Description:
 */
public class CompletableFutureTest {
    //全部线程执行完返回结果
    public static void main(String[] args) {
        // 1. 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<Integer> list = Arrays.asList(1, 2, 3);
        // 2. 提交任务，并调用join()阻塞等待所有任务执行完成
        CompletableFuture
                .allOf(
                        list.stream().map(key ->
                                CompletableFuture.runAsync(() -> {
                                    // 睡眠一秒，模仿处理过程
                                    try {
                                        Thread.sleep(1000L);
                                    } catch (InterruptedException e) {
                                    }
                                    System.out.println("结果" + key);
                                }, executorService))
                                .toArray(CompletableFuture[]::new))
                .join();
        executorService.shutdown();
    }
    //任一线程执行完返回结果
    /*public static void main(String[] args) {
        // 1. 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<Integer> list = Arrays.asList(1, 2, 3);
        long start = System.currentTimeMillis();
        // 2. 提交任务
        CompletableFuture<Object> completableFuture = CompletableFuture
                .anyOf(
                        list.stream().map(key ->
                                CompletableFuture.supplyAsync(() -> {
                                    // 睡眠一秒，模仿处理过程
                                    try {
                                        Thread.sleep(1000L);
                                    } catch (InterruptedException e) {
                                    }
                                    return "结果" + key;
                                }, executorService))
                                .toArray(CompletableFuture[]::new));
        executorService.shutdown();

        // 3. 获取结果
        System.out.println(completableFuture.join());
    }*/
    //一个线程执行完，交给另一个线程接着执行
    /*public static void main(String[] args) {
        // 1. 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 2. 提交任务，并调用join()阻塞等待任务执行完成
        String result2 = CompletableFuture.supplyAsync(() -> {
            // 睡眠一秒，模仿处理过程
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
            }
            return "结果1";
        }, executorService).thenApplyAsync(result1 -> {
            // 睡眠一秒，模仿处理过程
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
            }
            return result1 + "结果2";
        }, executorService).join();

        executorService.shutdown();
        // 3. 获取结果
        System.out.println(result2);
    }*/
}
