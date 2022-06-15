package imooc.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author chenlufeng
 * @date 2021/9/24
 * 高并发场景下 LongAdder 比AtomicLong 性能好
 */
public class AtomicLongDemo {
    public static void main(String[] args) {
        AtomicLong counter = new AtomicLong(0);
        ExecutorService service = Executors.newFixedThreadPool(20);
        long start = System.currentTimeMillis();
        for (int i = 0;i < 10000; i++){
            service.submit(new Task(counter));
        }
        service.shutdown();
        while(!service.isTerminated()){

        }
        long end = System.currentTimeMillis();
        System.out.println(counter.get());
        System.out.println("AtomicLong 耗时：" + (end - start));

    }

    private static class Task implements Runnable {

        private AtomicLong counter;

        public Task(AtomicLong counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counter.incrementAndGet();
            }
        }
    }
}
