package imooc.cache;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author chenlufeng
 * @date 2021/10/8
 */
public class Cache1 {
    private final HashMap<String,Integer> cache = new HashMap<>();

    public synchronized Integer compute(String userId) throws InterruptedException {
        Integer result = cache.get(userId);
        if (result == null){
            result = doCompute(userId);
            cache.put(userId,result);
        }
        return result;
    }

    private Integer doCompute(String userId) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        return new Integer(userId);
    }

    public static void main(String[] args) throws InterruptedException {
        Cache1 cache1 = new Cache1();
        System.out.println("开始计算了");
        Integer result = cache1.compute("13");
        System.out.println("第一次计算结果："+result);
        result = cache1.compute("13");
        System.out.println("第二次计算结果：" + result);
    }

}
