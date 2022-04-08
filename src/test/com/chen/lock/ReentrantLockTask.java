package com.chen.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenlufeng
 * @date 2021/6/28
 */
public class ReentrantLockTask extends LockTask{
    private static ReentrantLock locker = new ReentrantLock();
    public ReentrantLockTask(Boolean write) {
        super(write);
    }

    @Override
    protected void doTask() {
        locker.lock();
        try {
            if (write){
                counter++;
                hashMap.put(counter,"Data " + counter);
            }else {
                hashMap.get(counter);
            }
        }finally {
            locker.unlock();
        }
    }
}
