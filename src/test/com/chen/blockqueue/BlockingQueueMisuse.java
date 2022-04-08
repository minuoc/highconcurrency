package com.chen.blockqueue;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author chenlufeng
 * @date 2021/6/28
 */

@Slf4j
public class BlockingQueueMisuse {
    LinkedTransferQueue<String> linkedTransferQueue = new LinkedTransferQueue<>();

    @Test
    public void test() throws InterruptedException {
        int taskCount = 4000;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("misuse");

//        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        ExecutorService threadPool = Executors.newCachedThreadPool();
        IntStream.rangeClosed(1,taskCount).forEach(i -> threadPool.submit(()->{
            try {
                linkedTransferQueue.transfer("message " + i);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }));

        IntStream.rangeClosed(1,taskCount).forEach(i -> threadPool.submit(()->{
            try {
                log.debug("Got:{}", linkedTransferQueue.take());
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }));

        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());

    }

}
