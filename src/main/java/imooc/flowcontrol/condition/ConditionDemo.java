package imooc.flowcontrol.condition;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenlufeng
 * @date 2021/9/15
 */
public class ConditionDemo {

    private int size = 100;
    private PriorityQueue<Integer> queue = new PriorityQueue(size);

    private Lock lock = new ReentrantLock();

    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public static void main(String[] args) {
        ConditionDemo conditionDemo = new ConditionDemo();
        Producer producer = conditionDemo.new Producer();
        Consumer consumer = conditionDemo.new Consumer();
        producer.start();
        consumer.start();


    }


    class Consumer extends Thread {

        @Override
        public void run() {

            consume();

        }

        private void consume() {

            while(true) {
                lock.lock();
                try {
                    while(queue.size() == 0){
                        System.out.println("队列空，等待数据");
                        try {
                            notEmpty.await();
                        } catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                    queue.poll();
                    notFull.signalAll();
                    System.out.println("从队列中取出一个元素，队列剩余"+queue.size()+"个元素");
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    class Producer extends Thread {

        @Override
        public void run(){
            produce();
        }

        private void produce() {
            while(true){
                lock.lock();
                try {
                    while(queue.size() == size){
                        System.out.println("队列满，等待消费数据");
                        try {
                            notFull.await();
                        } catch (InterruptedException e){
                            e.printStackTrace();
                        }

                    }
                    queue.offer(1);
                    notEmpty.signalAll();
                    System.out.println("向队列插入一个元素，队列的剩余空间" + (size - queue.size()));

                }finally {
                    lock.unlock();
                }
            }
        }
    }

}


