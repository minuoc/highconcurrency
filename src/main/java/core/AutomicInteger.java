package core;

import java.util.concurrent.atomic.AtomicInteger;

public class AutomicInteger implements Runnable{

    static AtomicInteger safeCount = new AtomicInteger(0);

    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            safeCount.getAndIncrement();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AutomicInteger automicInteger = new AutomicInteger();
        Thread t1 = new Thread(automicInteger);
        Thread t2 = new Thread(automicInteger);
        t1.start();
        t2.start();
        Thread.sleep(500);
        System.out.println(automicInteger.safeCount.get());
    }
}