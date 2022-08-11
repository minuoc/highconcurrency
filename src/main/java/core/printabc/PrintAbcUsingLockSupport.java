package core.printabc;

import java.util.concurrent.locks.LockSupport;

public class PrintAbcUsingLockSupport {
    private static Thread threadA, threadB, threadC;

    public static void main(String[] args) {
        threadA = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName());
                //第一个 是A 线程打印 所以 不是线阻塞的
                LockSupport.unpark(threadB);
                LockSupport.park(threadA);

            }
        },"A");
        threadB = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                // 先阻塞 等待被唤醒
                LockSupport.park(threadB);
                System.out.println(Thread.currentThread().getName());
                LockSupport.unpark(threadC);


            }
        },"C");

        threadC = new Thread(() -> {
            for (int i = 0; i < 10; i++) {

                // 先阻塞 等待被唤醒
                LockSupport.unpark(threadA);
                System.out.println(Thread.currentThread().getName());
                LockSupport.park(threadC);

            }
        },"C");


        threadA.start();
        threadB.start();
        threadC.start();
    }
}
