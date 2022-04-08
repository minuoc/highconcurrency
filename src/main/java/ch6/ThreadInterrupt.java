package ch6;

import java.util.Date;

public class ThreadInterrupt {
    private static final Object MONITOR = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread( () -> {
            while(true){
                synchronized (MONITOR){
                    try {
                        MONITOR.wait();

                    }catch (InterruptedException e){
                        e.printStackTrace();
                        System.out.println(Thread.interrupted() + " " + new Date());
                    }
                }
            }
        });

        t.start();
        Thread.sleep(1_000);
        System.out.println(t.isInterrupted() + " " + new Date());
        t.interrupt();
        System.out.println(t.isInterrupted() + " " + new Date());
    }

}
