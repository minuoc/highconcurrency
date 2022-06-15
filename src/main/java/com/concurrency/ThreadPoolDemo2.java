package com.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author chenlufeng
 * @date 2022/6/15
 */
public class ThreadPoolDemo2 {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(2,4,0L,
                TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>(10),new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 10; i++) {
            int index = i;
            //采用submit 不会 报错
//            executorService.submit(() -> divTask(100,index));
            //使用 execute 会报错
            executorService.execute(() -> divTask(100,index));
        }
        executorService.shutdown();
    }

    private static void divTask(int a, int b) {
        double result = a / b;
        System.out.println(result);
    }
}
