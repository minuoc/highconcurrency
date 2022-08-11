package test;

import java.util.concurrent.CountDownLatch;

public class CountLanchTest {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        char[] aI = "abcdefghi".toCharArray();
        char[] aC = "123456789".toCharArray();


        new Thread(() -> {
            for (char c : aI) {
                System.out.println(c);
                countDownLatch.countDown();
            }

        }, "t1").start();

        new Thread(() -> {
            for (char c : aC) {
                System.out.println(c);
            }
        }, "t2").start();
    }
}
