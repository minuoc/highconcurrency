package com.concurrency;

/**
 * @author chenlufeng
 * @date 2022/6/14
 */
public class NofityDemo {
    public static void main(String[] args) {
//        Factory factory = new Factory();

        FactoryByCondition factory = new FactoryByCondition();

        //线程假死 状态
        for (int i = 0; i < 10; i++) {
            Thread producer = new Thread( () -> {
                try {
                    factory.put();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"生产者" + i);
            producer.start();
        }


        for (int i = 0; i < 10; i++) {
            Thread consumer = new Thread(() -> {
                try {
                    factory.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"消费者" + i);

            consumer.start();
        }


    }
}
