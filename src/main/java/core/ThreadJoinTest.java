package core;

import ch5.JoinTest;

/**
 * @author chenlufeng
 * @date 2021/9/24
 */
public class ThreadJoinTest extends Thread{
    int i;
    Thread previousThread;

    public ThreadJoinTest(int i, Thread previousThread) {
        this.i = i;
        this.previousThread = previousThread;
    }

    @Override
    public void run(){
        try {
            previousThread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("num:" + i);
    }

    public static void main(String[] args) {
        Thread previousThread = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            ThreadJoinTest joinTest = new ThreadJoinTest(i,previousThread);
            joinTest.start();
            previousThread = joinTest;
        }
    }

}
