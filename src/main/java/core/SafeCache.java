package core;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SafeCache {
    private final Map<String,Object> cache = new HashMap<>();

    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();

    public Object get(String key){
        readLock.lock();
        try{
            return cache.get(key);

        }finally {
            readLock.unlock();
        }
    }

    public Object put(String key,Object value){
        writeLock.lock();
        try{
            return cache.put(key,value);

        }finally {
            writeLock.unlock();
        }
    }


    public static void main(String[] args) {

    }
}
