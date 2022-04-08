package imooc.queue;

import java.util.concurrent.*;


/**
 * @author chenlufeng
 * @date 2021/9/10
 */
public class ProducerConcumerService {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        ThreadPoolExecutor producerPool = new ThreadPoolExecutor(5, 10,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(20)
                , Executors.defaultThreadFactory());


        for (int i = 0; i < 15; i++) {
            producerPool.execute(producer);
        }


        for (int i = 0; i < 10; i++) {
            producerPool.execute(consumer);
        }
        producerPool.shutdown();


    }
}
