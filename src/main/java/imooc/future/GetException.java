package imooc.future;

import java.util.concurrent.*;

/**
 * @author chenlufeng
 * @date 2021/10/6
 *
 * 演示 get 方法过程中抛出异常 for 循环为了演示抛出Exception 的 时机，
 * 并不是说一产生异常就抛出，直到我们get执行的时候，才会抛出
 */
public class GetException {

    public static void main(String[] args) {
        ExecutorService executorService  = Executors.newFixedThreadPool(20);
        Future<Integer> future = executorService.submit(new CallableTask());
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
                Thread.sleep(500);
            }
            System.out.println(future.isDone());
            future.get();
        }catch (InterruptedException e){
            e.printStackTrace();
            System.out.println("InterruptedException 异常");
        }catch (ExecutionException e){
            e.printStackTrace();
            System.out.println("ExecutionException 异常");
        }
    }

    static class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            throw new IllegalArgumentException("callable　抛出异常");
        }
    }
}
