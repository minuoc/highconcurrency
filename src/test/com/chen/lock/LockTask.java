package com.chen.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @author chenlufeng
 * @date 2021/6/28
 */
@Slf4j
public abstract class LockTask implements Runnable{
    protected volatile static long counter;
    protected boolean write;
    protected static HashMap<Long,String> hashMap = new HashMap<>();
    int loopCount;
    CountDownLatch start;
    CountDownLatch finish;

    public LockTask(Boolean write){
        this.write = write;
    }


    @Override
    public void run() {
        try {
            start.wait();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        for (int i = 0; i < loopCount; i++){
            doTask();
        }
    }

    abstract protected void doTask();
}
