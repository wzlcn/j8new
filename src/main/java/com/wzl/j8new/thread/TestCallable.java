package com.wzl.j8new.thread;

import java.util.concurrent.*;

/**
 * @author wangzhilong
 * @date 2020/10/16 16:37
 * @Description:
 */
public class TestCallable implements Callable<Object> {
    @Override
    public Object call() throws Exception {
        return Thread.currentThread().getName();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*//callable接口4步走
        TestCallable t1 = new TestCallable();
        TestCallable t2 = new TestCallable();
        TestCallable t3 = new TestCallable();
        //1：创建线程池
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(3);
        //2：将线程交由线程池管理
        Future<Object> rs1 = threadPool.submit(t1);
        Future<Object> rs2 = threadPool.submit(t2);
        Future<Object> rs3 = threadPool.submit(t3);
        //3:获取返回结果
        System.out.println(rs1.get());
        System.out.println(rs2.get());
        System.out.println(rs3.get());
        //4：关闭线程池
        threadPool.shutdown();*/

        new Thread() {
            @Override
            public void run() {
                super.run();
            }
        }.start();
    }
}
