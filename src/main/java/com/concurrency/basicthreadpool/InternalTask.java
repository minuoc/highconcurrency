package com.concurrency.basicthreadpool;

/**
 * @author chenlufeng
 * @date 2022/6/15
 */
public class InternalTask implements Runnable{

    private final RunnableQueue runnableQueue;

    //运行标记
    private volatile boolean running = true;

    public InternalTask(RunnableQueue runnableQueue, boolean running) {
        this.runnableQueue = runnableQueue;
        this.running = running;
    }

    @Override
    public void run() {
        while(running && !Thread.currentThread().isInterrupted()) {
            try {
                Runnable task = runnableQueue.take();
                task.run();
            } catch (InterruptedException e) {
                running = false;
                break;
            }
        }
    }


    public void stop() {
        this.running = false;
    }
}
