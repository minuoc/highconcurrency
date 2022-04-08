package com.chen.lock;

import lombok.extern.slf4j.Slf4j;

/**
 * @author chenlufeng
 * @date 2021/6/28
 */
@Slf4j
public class SyncTask extends LockTask{
    private static Object locker = new Object();

    public SyncTask(Boolean write) {
        super(write);
    }

    @Override
    protected void doTask() {
        synchronized (locker){
            if (write){
                counter ++;
                hashMap.put(counter,"Data " + counter);
            }else {
                hashMap.get(counter);
            }
        }
    }
}
