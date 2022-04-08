package imooc.immutable;

/**
 * @author chenlufeng
 * @date 2021/9/25
 * <p>
 * 演示栈封闭的两种情况，基本变量和对象
 */
public class StackConfinement implements Runnable {

    int index = 0;

    public void inThread() {
        int neverGoOut = 0;
        for (int i = 0; i < 10000; i++) {
            neverGoOut++;
        }
        System.out.println(Thread.currentThread().getName() + "栈内保护的数字是线程安全的" + neverGoOut);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            index++;
        }
        inThread();
    }

    public static void main(String[] args) throws InterruptedException {
        StackConfinement r = new StackConfinement();
        Thread t1 = new Thread(r,"t1");
        Thread t2 = new Thread(r,"t2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(r.index);

    }
}
