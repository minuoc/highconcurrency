package core;

import java.util.concurrent.locks.Lock;

/**
 * @author chenlufeng
 * @date 2021/10/5
 */
public class PrintABCUsingWaitNotify {
    private int state;
    private int times;
    private static final Object lock = new Object();


    public PrintABCUsingWaitNotify(int times) {
        this.times = times;
    }



    private void printLetter(String name, int targetState){
        for (int i = 0; i < times; i++) {
            synchronized (lock) {
                while (state % 3 != targetState) {
                    try {
                        lock.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                state++;
                System.out.print(name);
                if (state % 3 == 0){
                    System.out.println();
                }
                lock.notifyAll();
            }

        }
    }

    public static void main(String[] args) {
        PrintABCUsingWaitNotify printABC = new PrintABCUsingWaitNotify(10);
        new Thread(()->{
            printABC.printLetter("A",0);
        },"A").start();

        new Thread(()->{
            printABC.printLetter("B",1);
        },"B").start();

        new Thread(()->{
            printABC.printLetter("C",2);
        },"C").start();


    }


}
