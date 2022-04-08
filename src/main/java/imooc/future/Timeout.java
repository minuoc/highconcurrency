package imooc.future;

import java.sql.Time;
import java.util.concurrent.*;

/**
 * @author chenlufeng
 * @date 2021/10/6
 */
public class Timeout {

    private static final Ad DEFAULT_AD = new Ad("无网络时的默认广告");
    private static final ExecutorService exec = Executors.newFixedThreadPool(10);


    static class Ad {
        String name;

        public Ad(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Ad{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    static class FetchAdTask implements Callable<Ad> {

        @Override
        public Ad call() throws Exception {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e){
                System.out.println("sleep 期间被中断了");
                return new Ad("被中断时候的默认广告");
            }
            return new Ad("旅游订票 上携程");
        }
    }

    public void printAd() {
        Future<Ad> f = exec.submit(new FetchAdTask());
        Ad ad;
        try {
            ad = f.get(4000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e){
            ad = new Ad("被中断时的默认广告");
        } catch (ExecutionException e){
            ad = new Ad("异常时的默认广告");
        } catch (TimeoutException e){
            ad = new Ad("超时的默认广告");
            System.out.println("超时，未获取到广告");
            boolean cancel = f.cancel(true);
            System.out.println("cancel的结果:" + cancel);
        }
        exec.shutdown();
        System.out.println(ad);
    }


    public static void main(String[] args) {
        Timeout timeout = new Timeout();
        timeout.printAd();
    }
}

