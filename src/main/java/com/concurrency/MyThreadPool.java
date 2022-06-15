package com.concurrency;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @author chenlufeng
 * @date 2022/6/14
 */
public class MyThreadPool {
    LinkedList<Task> taskList = new LinkedList<>();

    class Task {
        int id;
        Task(int id) {
            this.id = id;
            System.out.println("第" + id + "个任务产生");
        }

        public void run() {
            System.out.println("第" + id + "个任务正在执行");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第" + id + "个任务执行完毕");
        }

    }

    class Worker extends Thread {
        String name;
        Worker(String name) {
            this.name = name;
        }
        public void run(){
            while (true) {
                if (taskList.size() == 0) {
                    try {
                        synchronized (taskList) {
                            System.out.println("Worker " + name + " 没有任务");
                            taskList.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                synchronized (taskList) {
                    System.out.println("Worker " + name + " 得到任务");
                    taskList.removeFirst().run();
                }

            }
        }

    }

    void pool() {
        ArrayList<Worker> workerArrayList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Worker k = new Worker("第" + (i+1) + "个工人");
            k.start();
            workerArrayList.add(k);
        }
    }


    class Factory extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (taskList) {
                    taskList.addLast(new Task(i+1));
                    taskList.notify();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool();
        //初始化工人
        myThreadPool.pool();
        MyThreadPool.Factory factory = myThreadPool.new Factory();
        factory.start();
    }

}
