package com.chen.blockqueue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chenlufeng
 * @date 2021/6/23
 */

@Slf4j
public class Consumer extends Worker{
    private static AtomicInteger totalConsumedAfterShutdown  = new AtomicInteger();

    public Consumer(String name, BlockingQueue<Integer> queue) {
        super(name, queue);
    }

    public static int totalConsumedAfterShutDown(){
        return totalConsumedAfterShutdown.get();
    }

    @Override
    public void run() {
        while(enable || queue.size() > 0){
            try {
                Integer item = queue.take();
//                Integer item = queue.poll(1,TimeUnit.SECONDS);
                log.info("size:{},got:{},enable:{}",queue.size(),item,enable);
                if (!enable){
                    totalConsumedAfterShutdown.incrementAndGet();
                }
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("{} quit",name);
    }
}
