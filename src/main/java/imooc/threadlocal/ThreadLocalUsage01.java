package imooc.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 10 个线程打印日期
 * @author chenlufeng
 * @date 2021/9/17
 */
public class ThreadLocalUsage01 {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 30; i++) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalUsage01().date(finalI);
                    System.out.println(date);
                }
            }).start();
            Thread.sleep(100);
        }


    }

    public String date(int seconds){
        Date date = new Date(seconds* 1000);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(date);

    }
}
