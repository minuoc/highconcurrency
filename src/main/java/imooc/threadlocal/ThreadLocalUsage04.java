package imooc.threadlocal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 加锁来解决 线程安全问题
 * @author chenlufeng
 * @date 2021/9/17
 */
public class ThreadLocalUsage04 {

    private static ExecutorService executorService = Executors.newFixedThreadPool(10);
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static List<String> stringList = new ArrayList<>();


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            Future<String> stringFuture =  executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    String date = new ThreadLocalUsage04().date(finalI);
                    return date;
                }
            });
            System.out.println(stringFuture.get());
        }
        System.out.println(stringList.stream().distinct().count());
        executorService.shutdown();


    }

    public String date(int seconds) {


        Date date = new Date(seconds * 1000);
        String s = null;
        synchronized (ThreadLocalUsage04.class) {
             s = dateFormat.format(date);
             stringList.add(s);
        }

        return s;

    }
}
