package imooc.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.stream.IntStream;

/**
 * @author chenlufeng
 * @date 2021/9/25
 */
public class LongAccumulatorDemo {
    public static void main(String[] args) {
//        LongAccumulator accumulator = new LongAccumulator((x,y)-> x+y,0);
        LongAccumulator accumulator = new LongAccumulator((x,y)-> 2 + x*y,1);

        ExecutorService executorService = Executors.newFixedThreadPool(20);
        IntStream.range(1,10).forEach(i->executorService.submit(()->accumulator.accumulate(i)));
        executorService.shutdown();
        while(!executorService.isTerminated()){

        }
        System.out.println(accumulator.getThenReset());

    }


}
