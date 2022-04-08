package core;

import java.util.concurrent.CyclicBarrier;

/**
 * @author chenlufeng
 * @date 2021/9/13
 */
public class CirleBarierTest {
    public static void main(String[] args) {
        int n = 4;
        CyclicBarrier barrier = new CyclicBarrier(n);
        for (int i = 0; i < n; i++) {

        }
    }

    static class Writer extends Thread {

    }
}
