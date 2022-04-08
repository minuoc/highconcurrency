package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chenlufeng
 * @date 2021/4/2
 */
public class ForLoop {
    public static void main(String[] args) {
        ExecutorService executorService  = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new Task());
        }
    }


}
class Task implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch ( Exception e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName());
    }
}
