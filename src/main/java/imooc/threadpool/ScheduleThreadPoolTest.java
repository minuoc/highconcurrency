package imooc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author chenlufeng
 * @date 2021/9/16
 */
public class ScheduleThreadPoolTest {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);
        executorService.scheduleAtFixedRate(new Task(),3000, 3000,TimeUnit.MILLISECONDS);

    }
}
