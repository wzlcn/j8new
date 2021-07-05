package com.wzl.j8new.designpatterns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @author wangzhilong
 * @date 2021/6/21 10:26
 * @Description: 观察者模式
 */
public class ObserverObject {

    private static final Logger logger = LoggerFactory.getLogger(ObserverObject.class);

    public static void main(String[] message) {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Future<String> future = pool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.MILLISECONDS.sleep(3000);
                return new String("success");
            }
        });

        try {
            System.out.println(future.get(2000, TimeUnit.MILLISECONDS));
        } catch (Exception e) {
            logger.error("未获取到异步执行结果！");
            e.printStackTrace();
        }finally {
            pool.shutdown();
        }
    }
}
