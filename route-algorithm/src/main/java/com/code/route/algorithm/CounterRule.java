package com.code.route.algorithm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2020-11-15.17:12
 */
public class CounterRule {


    private static final AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        atomicIntegerTest();

        Thread.sleep(3000);
        System.out.println("最终结果是" + count.get());
    }

    private static void atomicIntegerTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(10000);
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < 4; j++) {
                    System.out.println("count : " + increment());
                }
            });
        }
        executorService.shutdown();
    }

    /**
     * 计数机 可定制几次调用一次
     *
     * @return
     */
    public static int increment() {
//        if (count.getAndIncrement() == 5) {
//            count.set(0);
//            System.out.println("set count 0");
//        }
//        return count.get();

        return count.getAndIncrement();

    }

}
