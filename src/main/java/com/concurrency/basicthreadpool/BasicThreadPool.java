package com.concurrency.basicthreadpool;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chenlufeng
 * @date 2022/6/15
 */
public class BasicThreadPool extends Thread implements ThreadPool{

    //初始化线程数量
    private final int initSize;

    //线程池最大线程数量
    private final int maxSize;

    //线程池核心线程数量
    private final int coreSize;

    //当前活跃的线程数量
    private int activeCount;

    //创建线程所需的工厂
    private final ThreadFactory threadFactory;

    //任务队列
    private final RunnableQueue runnableQueue;

    //线程池是否已被shutdown
    private volatile boolean isShutdown = false;

    //工作线程队列
    private final Queue<ThreadTask> threadQueue = new ArrayDeque<>();

    private final static DenyPolicy DEFAULT_DENY_POLICY = new DenyPolicy.DiscardDenyPolicy();

    private final static ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();

    private final long keeyAliveTime;

    private final TimeUnit timeUnit;

    public BasicThreadPool(int initSize, int maxSize, int coreSize, int queueSize){
        this(initSize,maxSize,coreSize,DEFAULT_THREAD_FACTORY,queueSize,
                DEFAULT_DENY_POLICY,10,TimeUnit.SECONDS);
    }



    public BasicThreadPool(int initSize, int maxSize, int coreSize, ThreadFactory threadFactory,
                           int queueSize,DenyPolicy denyPolicy, long keeyAliveTime, TimeUnit timeUnit) {
        this.initSize = initSize;
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.threadFactory = threadFactory;
        this.runnableQueue = new LinkedRunnableQueue(queueSize, denyPolicy,this);
        this.keeyAliveTime = keeyAliveTime;
        this.timeUnit = timeUnit;
        this.init();
    }

    private void init(){
        start();
        for (int i = 0; i < initSize; i++){
            newThread();
        }
    }

    private void newThread() {
        InternalTask internalTask = new InternalTask(runnableQueue);
        Thread thread = this.threadFactory.createThread(internalTask);
        ThreadTask threadTask = new ThreadTask(thread,internalTask);
        threadQueue.offer(threadTask);
        this.activeCount ++;
        thread.start();
    }

    private void removeThread() {
        ThreadTask threadTask = threadQueue.remove();
        //任务 不执行
        threadTask.internalTask.stop();
        this.activeCount --;
    }


    @Override
    public void execute(Runnable runnable) {
        if (this.isShutdown){
            throw new IllegalStateException("The thread pool is destory");
        }
        this.runnableQueue.offer(runnable);
    }

    @Override
    public void run() {
        while (!isShutdown && !isInterrupted()){
            try {
                timeUnit.sleep(keeyAliveTime);
            } catch (InterruptedException e) {
                isShutdown = true;
                break;
            }

            synchronized (this) {
                if (isShutdown) {
                    break;
                }
                System.out.println(runnableQueue.size() + "==" + activeCount);
                if (runnableQueue.size() > 0 && activeCount < coreSize) {
                    for (int i = initSize; i < coreSize; i++) {
                        System.out.println("--create");
                        newThread();
                    }
                    continue;
                }

                if (runnableQueue.size() > 0 && activeCount < maxSize) {
                    for (int i = coreSize; i < maxSize; i++) {
                        newThread();
                    }
                }

                if (runnableQueue.size() == 0 && activeCount > coreSize) {
                    for (int i = coreSize; i < activeCount; i ++) {
                        removeThread();
                    }
                }
            }
        }
    }



    @Override
    public void shutdown() {
        synchronized (this) {
            if (isShutdown) {
                return;
            }
            isShutdown = true;
            threadQueue.forEach(v -> {
                v.internalTask.stop();
                v.thread.interrupt();
            });
        }
    }

    @Override
    public int getInitSize() {
        if (isShutdown){
            throw new IllegalStateException("The thread pool is destroy");
        }
        return this.initSize;
    }

    @Override
    public int getMaxSize() {
        if (isShutdown){
            throw new IllegalStateException("The thread pool is destroy");
        }
        return this.maxSize;
    }

    @Override
    public int getCoreSize() {
        if (isShutdown){
            throw new IllegalStateException("The thread pool is destroy");
        }
        return this.coreSize;
    }

    @Override
    public int getQueueSize() {
        if (isShutdown){
            throw new IllegalStateException("The thread pool is destroy");
        }
        return this.runnableQueue.size();
    }

    @Override
    public int getActiveCount() {
        synchronized (this) {
            return this.activeCount;
        }
    }

    @Override
    public boolean isShutdown() {
        return this.isShutdown;
    }

    private static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger GROUP_COUNTER = new AtomicInteger(1);

        private static final ThreadGroup group = new ThreadGroup("MyThreadPool-" + GROUP_COUNTER.getAndDecrement());

        private static final AtomicInteger COUNTER = new AtomicInteger(0);

        @Override
        public Thread createThread(Runnable runnable) {
            return new Thread(group, runnable,"thread-pool-" + COUNTER.getAndDecrement());
        }
    }

    private static class ThreadTask {
        Thread thread;
        InternalTask internalTask;
        public ThreadTask(Thread thread,InternalTask internalTask) {
            this.thread = thread;
            this.internalTask = internalTask;
        }
    }


}
