package imooc.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chenlufeng
 * @date 2021/9/17
 */
public class ThreadLocalUsage00 {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String date = new ThreadLocalUsage00().date(10);
                System.out.println(date);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String date = new ThreadLocalUsage00().date(104707);
                System.out.println(date);
            }
        }).start();
    }

    public String date(int seconds){
        Date date = new Date(seconds* 1000);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(date);

    }
}
