package com.concurrency.basicthreadpool;

/**
 * @author chenlufeng
 * @date 2022/6/15
 */
public interface ThreadPool {

    void execute(Runnable runnable);

    void shutdown();

    int getInitSize();

    int getMaxSize();

    int getCoreSize();

    int getQueueSize();

    int getActiveCount();

    boolean isShutdown();
}
