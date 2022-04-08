package imooc.lock.lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenlufeng
 * @date 2021/9/22
 * 使用tryLock 来避免死锁
 */
public class TryLockDeadLock implements Runnable{
    int flag = 1;
    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        TryLockDeadLock r1 = new TryLockDeadLock();
        TryLockDeadLock r2 = new TryLockDeadLock();
        r1.flag = 1;
        r2.flag = 0;
        new Thread(r1).start();
        new Thread(r2).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (flag == 1){
                try {
                    if (lock1.tryLock(800, TimeUnit.MILLISECONDS)){
                        try {
                            System.out.println("线程1获取到了锁1");
                            Thread.sleep(new Random().nextInt(1000));

                            if (lock2.tryLock(800,TimeUnit.MILLISECONDS)){
                                try {
                                    System.out.println("线程1获取到了锁2");
                                    System.out.println("线程1获取到了两把锁");
                                    break;
                                } finally {
                                    lock2.unlock();
                                }
                            }else {
                                System.out.println("线程1获取锁2 失败,已重试");
                            }

                        }finally {
                            lock1.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    } else {
                        System.out.println("线程1 获取锁1失败,已重试");
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

            if (flag == 0){
                try {
                    if (lock1.tryLock(800, TimeUnit.MILLISECONDS)){
                        try {
                            System.out.println("线程2获取到了锁1");
                            Thread.sleep(new Random().nextInt(1000));

                            if (lock2.tryLock(800,TimeUnit.MILLISECONDS)){
                                try {
                                    System.out.println("线程2获取到了锁2");
                                    System.out.println("线程2获取到了两把锁");
                                    break;
                                } finally {
                                    lock2.unlock();
                                }
                            }else {
                                System.out.println("线程2获取锁2 失败,已重试");
                            }

                        }finally {
                            lock1.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    } else {
                        System.out.println("线程2 获取锁1失败,已重试");
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }


        }
    }
}
