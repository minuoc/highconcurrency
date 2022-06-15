package imooc.cache;

import imooc.cache.computable.Computable;
import imooc.cache.computable.ExpensiveFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenlufeng
 * @date 2021/10/8
 *
 *
 */
public class Cache2<A,V>  {
    private final Map<A,V> cache = new HashMap<>();
    private final Computable<A,V> c;

    public Cache2(Computable<A, V> c) {
        this.c = c;
    }

    public synchronized V compute(A arg) throws Exception {
        System.out.println("进入缓存机制");
        V result = cache.get(arg);
        if (result == null){
            result = c.compute(arg);
            cache.put(arg,result);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        Cache2<String,Integer> cache2 = new Cache2(new ExpensiveFunction());
        System.out.println("开始计算了");
        Integer result = cache2.compute("13");
        System.out.println("第一次计算结果："+result);
        result = cache2.compute("13");
        System.out.println("第二次计算结果：" + result);
    }
}
