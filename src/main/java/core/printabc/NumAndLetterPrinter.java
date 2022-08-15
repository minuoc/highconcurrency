package core.printabc;

/**
 * @author chenlufeng
 * @date 2022/8/15
 */

import java.util.concurrent.locks.LockSupport;

/**
 * 用两个线程 一个输出字母，一个输出数字 交替输出 1A2B3C4D...26Z
 */
public class NumAndLetterPrinter {
    private static Thread numThread,letterThread;

    public static void main(String[] args) {
        letterThread = new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                System.out.print((char)('A' + i));
                LockSupport.unpark(numThread);
                LockSupport.park();
            }
        },"letterThread");


        numThread = new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                System.out.print(i+1);
                LockSupport.park();
                LockSupport.unpark(letterThread);
            }
        },"numThread");
        numThread.start();
        letterThread.start();
    }

}
