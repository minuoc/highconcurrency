package core;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * @author chenlufeng
 * @date 2021/9/13
 */
public class CountDownLatchService {

    private CountDownLatch latch = new CountDownLatch(5);

    public CountDownLatchService() {

    }

    private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");



    public void doSomething(){
        try{

            System.out.println(Thread.currentThread().getName() + " doSomething start-" + getFormatTimeStr());
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " doSomething end-" + getFormatTimeStr());

        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            latch.countDown();
        }

    }

    public static String getFormatTimeStr(){
        return sf.format(new Date());
    }
}
