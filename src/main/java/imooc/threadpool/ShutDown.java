package imooc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chenlufeng
 * @date 2021/9/13
 */
public class ShutDown {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++){
            executorService.execute(new ShutDownTask());
        }

        Thread.sleep(5000);
        executorService.shutdown();
        executorService.execute(new ShutDownTask());
    }
}

class ShutDownTask implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
