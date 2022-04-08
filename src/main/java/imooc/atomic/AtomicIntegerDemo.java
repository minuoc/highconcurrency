package imooc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chenlufeng
 * @date 2021/9/24
 *
 * 使用原子类 保证了线程安全
 */
public class AtomicIntegerDemo implements Runnable{
    private static final AtomicInteger atomicInteger = new AtomicInteger(0);
    private static volatile int basisCount = 0;

    public void incrementBasic() {
        basisCount++;
    }

    public void incrementAtomic() {
//        atomicInteger.getAndIncrement();
        atomicInteger.getAndAdd(-90);
    }

    @Override
    public void run(){
        for (int i = 0; i < 1000; i++) {
            incrementAtomic();
            incrementBasic();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerDemo atomicIntegerDemo = new AtomicIntegerDemo();
        Thread t1 = new Thread(atomicIntegerDemo);
        Thread t2 = new Thread(atomicIntegerDemo);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(atomicInteger.get());
        System.out.println(basisCount);

    }
}
