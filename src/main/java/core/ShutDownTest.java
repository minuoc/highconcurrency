package core;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenlufeng
 * @date 2021/9/17
 */
public class ShutDownTest {
    public static void main(String[] args) {
        InnerClass innerClass = new InnerClass();
        Thread thread = new Thread(innerClass);
        thread.start();
        long i = System.currentTimeMillis();
        while (System.currentTimeMillis() - i < 10*1000){
            thread.isAlive();
        }
        thread.interrupt();
    }

    static class InnerClass implements Runnable {

        @Override
        public void run() {
            System.err.println("start work");
            while(!Thread.currentThread().isInterrupted()){
                System.out.println("doing work");
            }
            System.err.println("done work");

        }
    }
}
