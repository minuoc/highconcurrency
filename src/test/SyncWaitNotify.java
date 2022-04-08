package test;

import java.util.concurrent.locks.LockSupport;

public class SyncWaitNotify {

    private static volatile boolean t2Started = false;


    public static void main(String[] args) {
        final Object o = new Object();

        char[] aI = "abcdefghi".toCharArray();
        char[] aC = "123456789".toCharArray();

        new Thread(() -> {
            synchronized (o){
                for(char c : aI) {
                    System.out.println(c);


                }
            }


        },"t1").start();

        new Thread(() -> {

            synchronized (o){
                for(char c : aC) {
                    System.out.println(c);



                        o.notify();




                }
            }


        },"t2").start();


    }
}
