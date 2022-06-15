package imooc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chenlufeng
 * @date 2021/9/16
 */
public class CacheThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++){
            executorService.execute(new Task());
        }
    }
}
