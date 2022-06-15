package com.concurrency;

import java.util.concurrent.*;

/**
 * @author chenlufeng
 * @date 2022/6/15
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(4);
//        for (int i = 0; i < 10; i++) {
//            int index = i;
//            executorService.submit(()->{
//                System.out.println("i:" + index + " executorService");
//            });
//        }
//        executorService.shutdown();


        ExecutorService executorService = new ThreadPoolExecutor(2,4,0L,
                TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>(10),new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 10; i++) {
            int index = i;
            executorService.submit(() -> System.out.println("i:" + index + " executorService"));
        }
        executorService.shutdown();

    }
}
