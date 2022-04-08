package com.chen.lock;

import java.util.concurrent.locks.StampedLock;

/**
 * @author chenlufeng
 * @date 2021/6/28
 */
public class StampedLockTask extends LockTask{

    private static StampedLock locker = new StampedLock();

    public StampedLockTask(Boolean write) {
        super(write);
    }

    @Override
    protected void doTask() {
        if (write){
            long stamp = locker.writeLock();
            try {
                counter++;
                hashMap.put(counter,"Data " +counter);
            }finally {
                locker.unlockWrite(stamp);
            }
        }else {
            long stamp = locker.tryOptimisticRead();
            long value = counter;
            if (!locker.validate(stamp)){
                
            }
        }
    }
}
