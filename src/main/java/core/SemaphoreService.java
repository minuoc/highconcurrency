package core;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

public class SemaphoreService {
    private static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private static Semaphore semaphore = new Semaphore(5);

    public void doSomething(){
        try{
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName() + " doSomething start-" + getFormatTimeStr());
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " doSomething end-" + getFormatTimeStr());
            semaphore.release();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    public static String getFormatTimeStr(){
        return sf.format(new Date());
    }

}
