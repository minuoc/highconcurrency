package com.concurrency.basicthreadpool;

import java.util.LinkedList;

/**
 * @author chenlufeng
 * @date 2022/6/15
 */
public class LinkedRunnableQueue implements RunnableQueue{

    //任务队列的最大容量
    private final int limit;

    private final DenyPolicy denyPolicy;

    private final LinkedList<Runnable> runnableList = new LinkedList<>();

    private final ThreadPool threadPool;

    public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    @Override
    public void offer(Runnable runnable) {
        synchronized (runnableList) {
            if (runnableList.size() >= limit) {
                denyPolicy.reject(runnable,threadPool);
            }
        }
    }

    @Override
    public Runnable take() throws InterruptedException {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
