package imooc.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用线程池
 * @author chenlufeng
 * @date 2021/9/17
 */
public class ThreadLocalUsage02 {

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalUsage02().date(finalI);
                    System.out.println(date);
                }
            });
        }
        executorService.shutdown();

    }

    public String date(int seconds){
        Date date = new Date(seconds* 1000);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(date);

    }
}
