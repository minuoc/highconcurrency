package imooc.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenlufeng
 * @date 2021/9/22
 *
 *演示多线程 预订电影院座位
 */
public class CinemaBookSet {
    private static ReentrantLock lock = new ReentrantLock();

    private static void bookSeat(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始预订座位");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + "预订完成座位");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    public static void main(String[] args) {
        new Thread(() -> {
            bookSeat();
        }).start();
        new Thread(() -> {
            bookSeat();
        }).start();
        new Thread(() -> {
            bookSeat();
        }).start();
        new Thread(() -> {
            bookSeat();
        }).start();
    }

}
