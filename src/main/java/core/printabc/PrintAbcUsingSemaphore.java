package core.printabc;

import java.util.concurrent.Semaphore;

public class PrintAbcUsingSemaphore {
    private int times;
    //只有 A 初始信号量 为 1 第一次获取到的只能是A
    private static Semaphore semaphoreA = new Semaphore(1);
    private static Semaphore semaphoreB = new Semaphore(0);
    private static Semaphore semaphoreC = new Semaphore(0);

    public PrintAbcUsingSemaphore(int times) {
        this.times = times;
    }

    private void printLetter(String name,Semaphore current, Semaphore next) {
        for (int i = 0; i < times; i++) {
            try {
//                System.out.println("111" + Thread.currentThread().getName());
                current.acquire();
                System.out.println(name);
                next.release();
//                System.out.println("222" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    public static void main(String[] args) {
        PrintAbcUsingSemaphore printer = new PrintAbcUsingSemaphore(10);

        new Thread(() -> {
            printer.printLetter("A",  semaphoreA, semaphoreB);
        }, "A").start();
        new Thread(() -> {
            printer.printLetter("B", semaphoreB, semaphoreC);
        }, "B").start();
        new Thread(() -> {
            printer.printLetter("C", semaphoreC,semaphoreA);
        }, "C").start();
    }
}
