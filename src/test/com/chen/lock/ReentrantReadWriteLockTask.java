package com.chen.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author chenlufeng
 * @date 2021/6/28
 */
public class ReentrantReadWriteLockTask extends LockTask{
    private static ReentrantReadWriteLock locker = new ReentrantReadWriteLock();
    public ReentrantReadWriteLockTask(Boolean write) {
        super(write);
    }

    @Override
    protected void doTask() {
        if (write) {
            locker.writeLock().lock();
            try {
                counter++;
                hashMap.put(counter,"Data "+counter);
            }finally {
                locker.writeLock().unlock();
            }
        }else {
            locker.readLock().lock();
            try {
                hashMap.get(counter);
            } finally {
                locker.readLock().unlock();
            }
        }
    }
}
