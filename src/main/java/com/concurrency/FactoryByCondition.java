package com.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenlufeng
 * @date 2022/6/14
 */
public class FactoryByCondition {
    private int[] items = new int[1];
    private int size = 0;

    private Lock lock = new ReentrantLock();

    private Condition produceCondition = lock.newCondition();

    private Condition consumerCondition = lock.newCondition();


    public  void  put() throws InterruptedException {
        do {
            lock.lock();

            try{
                while(size == items.length) {
                    //生产者进入等待
                    System.out.println(Thread.currentThread().getName() + " 进入阻塞");
                    produceCondition.await();
                    System.out.println(Thread.currentThread().getName() + " 被唤醒");
                }
                System.out.println(Thread.currentThread().getName() + " 开始工作");
                //为了方便演示 设置 固定值
                items[0] = 1;
                size ++;
                System.out.println(Thread.currentThread().getName() + " 完成工作");
                //完成唤醒消费者
                consumerCondition.signal();

            } finally {
                lock.unlock();
            }

        }while (true);
    }

    public void take() throws InterruptedException {
        do {
            lock.lock();
            try {
                while (size == 0) {
                    //消费者阻塞等待
                    consumerCondition.await();
                }
                System.out.println("消费者工作~");
                size --;
                //唤醒生产者
                produceCondition.signal();
            } finally {
                lock.unlock();
            }

        } while (true);


    }
}
