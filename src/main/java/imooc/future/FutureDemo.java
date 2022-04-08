package imooc.future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author chenlufeng
 * @date 2021/10/5
 */
public class FutureDemo {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<Integer> future = service.submit(new CallableTask());
        try {
            System.out.println(future.get());
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }
        service.shutdown();

    }

    static class CallableTask implements Callable {

        @Override
        public Integer call() throws Exception {

            Thread.sleep(1000);
            return new Random().nextInt();
        }
    }
}
