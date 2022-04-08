package imooc.lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author chenlufeng
 * @date 2021/9/23
 */
public class Upgrading {
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);
    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    private static void readUpgrading(){
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到了读锁，正在读取");
        }finally {
            readLock.unlock();
        }
    }
}
