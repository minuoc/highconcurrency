package com.chen.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author chenlufeng
 * @date 2021/6/28
 */
public class FairReentrantReadWriteLockTask extends LockTask {
    public static ReentrantReadWriteLock locker = new ReentrantReadWriteLock(true);

    public FairReentrantReadWriteLockTask(Boolean write) {
        super(write);
    }

    @Override
    protected void doTask() {
        if (write) {
            locker.writeLock().lock();
            try {
                counter++;
                hashMap.put(counter, "Data " + counter);
            } finally {
                locker.writeLock().unlock();
            }

        } else {
            locker.readLock().lock();
            try {
                hashMap.get(counter);
            } finally {
                locker.readLock().unlock();
            }

        }
    }


}
