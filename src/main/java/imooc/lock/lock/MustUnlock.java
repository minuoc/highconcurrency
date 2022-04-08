package imooc.lock.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenlufeng
 * @date 2021/9/22
 *
 * lock 不会项synchronized 一样 异常的时候自动释放锁，
 * 最佳实践是 finally 中释放锁
 */
public class MustUnlock {

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        try{
            //获取本锁保护的资源
            System.out.println(Thread.currentThread().getName());
        }finally {

            lock.unlock();
        }

    }




}
