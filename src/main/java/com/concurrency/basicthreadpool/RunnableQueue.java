package com.concurrency.basicthreadpool;

/**
 * @author chenlufeng
 * @date 2022/6/15
 */
public interface RunnableQueue {

    void offer(Runnable runnable);

    Runnable take() throws InterruptedException;

    int size();
}
