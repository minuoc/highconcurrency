package ch9;

import java.util.stream.Stream;

/**
 * @author chenlufeng
 * @date 2022/8/4
 */
public class ProduceConsumerVersion3 {
    private int i = 0;
    private final Object LOCK = new Object();
    private volatile boolean isProduced = false;

    public void produce(){
        synchronized (LOCK) {
            while(isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "->" + (++i));
            LOCK.notifyAll();
            isProduced = true;
        }
    }


    public void consume() {
        synchronized (LOCK) {
            while (!isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "->" + i);
            LOCK.notifyAll();
            isProduced = false;
        }
    }


    public static void main(String[] args) {

        ProduceConsumerVersion3 pc = new ProduceConsumerVersion3();
        Stream.of("P1", "P2", "P3").forEach(n -> new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    pc.produce();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, n).start());

        Stream.of("C1", "C2", "C3", "C4").forEach(n -> new Thread(() -> {
            while (true) {
                pc.consume();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, n).start());
    }
}
