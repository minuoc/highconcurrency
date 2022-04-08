package com.chen.blockqueue;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chenlufeng
 * @date 2021/6/23
 */
@Slf4j
public class Producer extends Worker{

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public Producer(String name, BlockingQueue<Integer> queue) {
        super(name, queue);
    }


    @Override
    public void run() {
        while(enable){
            try {
                int value = atomicInteger.incrementAndGet();
                queue.put(value);
                TimeUnit.MILLISECONDS.sleep(100);
                log.info("size:{},put:{},enable:{}",queue.size(),value,enable);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        log.info("{} quit",name);

    }
}
