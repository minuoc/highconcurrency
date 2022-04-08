package imooc.flowcontrol.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author chenlufeng
 * @date 2021/10/5
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("所有人都到厂了，大家一起出发");
            }
        });
        for (int i = 0; i < 5; i++) {
            new Thread(new Task(i,cyclicBarrier)).start();

        }

    }

    static class Task implements Runnable {
        private int id;
        private CyclicBarrier cyclicBarrier;

        public Task(int id,CyclicBarrier cyclicBarrier) {
            this.id = id;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程" + id + "前往集合地点");
            try {
                Thread.sleep((long)Math.random() * 1000);
                System.out.println("线程" + id + "到了集合地点，等待其他人到达");
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e){
                e.printStackTrace();
            }

        }
    }
}
