package ch9;

import java.util.stream.Stream;

/**
 * @author chenlufeng
 * @date 2022/8/4
 */
public class DifferenceOfWaitAndSleep {
    public static final Object LOCK = new Object();

    public static void main(String[] args){
//        m1();
//        m2();


        Stream.of("T1","T2").forEach(name -> new Thread(DifferenceOfWaitAndSleep::m3,name).start());

    }

    //休眠两秒正常结束
    public static void m1() {
        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 未使用synchronized 则 抛出 java.lang.IllegalMonitorStateException 异常
    public static void m2() {
        synchronized (LOCK) {
            try {
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    // 两个线程 依次运行
    public static void m3() {
        synchronized (LOCK) {
            try {
                System.out.println("The Thread " + Thread.currentThread().getName() + " enter");
                Thread.sleep(20_000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    // 两个线程几乎同时运行， 两个线程最终都加入到了 LOCK 的 waiting queue
    public static void m4(){
        synchronized (LOCK) {
            try {
                System.out.println("The Thread " + Thread.currentThread().getName() + " enter");
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }






}
