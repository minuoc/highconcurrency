package ch1;

import java.util.concurrent.TimeUnit;

public class TryConcurrency {
    public static void main(String[] args) {
//        Thread t1 = new Thread("Custom-Thread"){
//            public void run(){
//                for (int i = 0; i < 1000; i++) {
//                    println("Task 1=>" + i);
//                    try {
//                        Thread.sleep(1000L);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//        t1.start();
//
//        for (int i = 0; i < 1000; i++) {
//            println("Task 2=>" + i);
//
//        }

        Thread t = new Thread("Read-Thread"){
            public void run(){
                readFromDataBase();
            }
        };
//        t.start();
//        t.start();
        t.run();
//        new Thread("Write-Thread"){
//            public void run(){
//                writeDataToFile();
//            }
//        }.start();

//        try {
//            TimeUnit.SECONDS.sleep(30);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        readFromDataBase();
//        writeDataToFile();
    }

    private static void readFromDataBase(){
        try{
            println("Begin read data from db.");
            Thread.sleep(1000 * 10l);
            println("Read data done and start handle it ");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        println("The data handle finish and successfully");
    }


    private static void writeDataToFile(){
        try{
            println("Begin write data from db.");
            Thread.sleep(2000 * 10l);
            println("Write data done and start handle it.");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        println("The data handle finish and successfully");
    }

    private static void println(String s) {
        System.out.println(s);
    }
}
