package core;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenlufeng
 * @date 2021/9/24
 */
public class ConcurrentHashMapTest {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        ConcurrentHashMap<String, AtomicInteger> map = new ConcurrentHashMap();
        map.put("key", new AtomicInteger(0));
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    map.get("key").incrementAndGet();
                }
            });

        }
        Thread.sleep(3000);
        System.out.println("---------------" + map.get("key") + "----------------");
        executorService.shutdown();


    }
}
