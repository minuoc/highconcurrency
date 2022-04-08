package imooc.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenlufeng
 * @date 2021/10/6
 */
public class FairLocked implements Runnable{
    private int seatNumber = 100;

    /**
     * 默认 是非公平锁
     */
    private ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while(true) {
            lock.lock();
            try {
                if (seatNumber > 0){
                    Thread.sleep(100);
                    --seatNumber;
                    System.out.println(Thread.currentThread().getName() + "占用一个座位，还剩余 " + seatNumber + "个座位");
                }else {
                    System.out.println(Thread.currentThread().getName() + ":不好意思，票卖完了");
                    break;
                }

            } catch (InterruptedException e){
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FairLocked fairLocked = new FairLocked();
        Thread t1 = new Thread(fairLocked,"A窗口");
        Thread t2 = new Thread(fairLocked,"B窗口");
        t1.start();
        t2.start();
    }
}
