package imooc.lock.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenlufeng
 * @date 2021/9/22
 */
public class LockDemo {

    public static void main(String[] args) {
        new LockDemo().init();
    }

    private void init() {
        final Outputer outputer = new Outputer();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(5);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    outputer.output("悟空");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(5);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    outputer.output("大师兄");
                }
            }
        }).start();
    }

    static class Outputer {
        Lock lock = new ReentrantLock();
        public void output(String name) {
            //字符串打印 一个个打印
            int len = name.length();
            lock.lock();
            try {
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println("");
            }finally {
                lock.unlock();
            }
        }
    }
}
