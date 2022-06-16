package com.concurrency.basicthreadpool;

/**
 * @author chenlufeng
 * @date 2022/6/15
 */
@FunctionalInterface
public interface ThreadFactory {
    //创建线程
    Thread createThread(Runnable runnable);
}
