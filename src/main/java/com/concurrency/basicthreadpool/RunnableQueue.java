package com.concurrency.basicthreadpool;

/**
 * @author chenlufeng
 * @date 2022/6/15
 */
//任务队列
public interface RunnableQueue {

    // 当有新的任务进来时 首先会offer 到队列中
    void offer(Runnable runnable);

    // 工作线程通过take 方法获取 Runnable
    Runnable take() throws InterruptedException;

    //获取任务队列中 任务的数量
    int size();
}
