package com.concurrency.basicthreadpool;

/**
 * @author chenlufeng
 * @date 2022/6/15
 */
public interface ThreadFactory {
    Thread createThread(Runnable runnable);
}
