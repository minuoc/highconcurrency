package core;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenlufeng
 * @date 2022/6/30
 */
public class ServiceLockInterruptibly {
    public ReentrantLock lock = new ReentrantLock();

    public void waitMethodLock() {
        lock.lock();
        try {
            System.out.println("lock begin" + Thread.currentThread().getName());
            for (int i = 0; i < Integer.MAX_VALUE / 10 ; i++) {
                String newString = new String();
                Math.random();
            }
            System.out.println("lock end" + Thread.currentThread().getName());
        } finally {
            lock.unlock();
        }
    }

    public void waitMethodLockInterruptibly()  {

        try {
            lock.lockInterruptibly();
            System.out.println("lock begin" + Thread.currentThread().getName());
            for (int i = 0; i < Integer.MAX_VALUE / 10 ; i++) {
                String newString = new String();
                Math.random();
            }
            System.out.println("lock end" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
