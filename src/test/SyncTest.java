package test;

import java.util.ArrayList;
import java.util.concurrent.locks.LockSupport;

public class SyncTest {
    static Thread t1 = null;
    static Thread t2 = null;

    public static void main(String[] args) {


        char[] aI = "abcdef".toCharArray();
        char[] aC = "1234567".toCharArray();


        t1 = new Thread(() -> {
            for(char c : aI){
                System.out.println(c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        },"t1");


        t2 = new Thread(() -> {
            for(char c: aC){
                LockSupport.park();
                System.out.println(c);
                LockSupport.unpark(t1);

            }
        },"t2");

        t1.start();
        t2.start();



    }
}
