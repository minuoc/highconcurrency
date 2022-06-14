package com.concurrency;

/**
 * @author chenlufeng
 * @date 2022/6/14
 */
public class Factory {
    //数据存储容器
    private int[] items = new int[1];
    //实际存储大小
    private int size = 0;

    public synchronized void put() throws InterruptedException {
        do {
            while (size == items.length) {
                System.out.println(Thread.currentThread().getName() + " 进入阻塞");
                this.wait();
                System.out.println(Thread.currentThread().getName() + " 被唤醒");
            }
            System.out.println(Thread.currentThread().getName() + " 开始工作");
            items[0] = 1;
            size ++;
            System.out.println(Thread.currentThread().getName() + " 完成工作");
            //当生产队列有数据之后通知唤醒消费者
            this.notify();
        } while (true);
    }

    public synchronized void take() throws InterruptedException {
        do {
            while (size == 0) {
                System.out.println(Thread.currentThread().getName() + " 进入阻塞(消费者)");
                this.wait();
                System.out.println(Thread.currentThread().getName() + " 被唤醒(消费者)");
            }
            System.out.println("消费者工作~");
            size --;
            //唤醒生产者 可以添加生产了
            this.notify();
        } while (true);
    }
}
