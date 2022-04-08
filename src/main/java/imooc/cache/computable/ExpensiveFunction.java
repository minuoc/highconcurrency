package imooc.cache.computable;

import java.util.concurrent.TimeUnit;

/**
 * @author chenlufeng
 * @date 2021/10/8
 */
public class ExpensiveFunction implements Computable<String,Integer>{
    @Override
    public Integer compute(String arg) throws Exception {
        TimeUnit.SECONDS.sleep(5);
        return Integer.valueOf(arg);
    }


}
