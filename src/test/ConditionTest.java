package test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {


    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition conditionT1 = lock.newCondition();
        Condition conditionT2 = lock.newCondition();

        char[] aI = "abcdefghi".toCharArray();
        char[] aC = "123456789".toCharArray();


        new Thread(() -> {

            lock.lock();
            try{
                for (char c : aC) {
                    System.out.println(c);
                    conditionT2.signal();
                    conditionT1.await();
                }

            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }



        }, "t1").start();


        new Thread(() -> {


            lock.lock();
            try{
                for (char c : aI) {
                    System.out.println(c);
                    conditionT1.signal();
                    conditionT2.await();
                }

            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }


        }, "t2").start();
    }
}
