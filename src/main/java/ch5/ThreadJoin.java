package ch5;

import java.util.stream.IntStream;

public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            IntStream.range(1,1000).forEach(i -> System.out.println(Thread.currentThread().getName() + "----index----" + i));
        },"thread1");

        Thread t2 = new Thread(()->{
            IntStream.range(1,1000).forEach(i -> System.out.println(Thread.currentThread().getName() + "----index----" + i));

        },"thread2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        IntStream.range(1,1000)
                .forEach(i -> System.out.println(Thread.currentThread().getName() + "----index----" + i));
    }
}
