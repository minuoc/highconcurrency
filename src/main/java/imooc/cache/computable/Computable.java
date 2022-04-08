package imooc.cache.computable;

/**
 * @author chenlufeng
 * @date 2021/10/8
 */
public interface Computable<A,V> {
    V compute(A arg) throws Exception;
}
