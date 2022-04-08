package ch3;

public class CreateThread {
    public static void main(String[] args) {
        Thread t1 = new Thread();
        Thread t2 = new Thread(){
            public void run(){
                System.out.println("=====================");
            }

        };

        t1.start();
        t2.start();
        System.out.println(t1.getName());
        System.out.println(t2.getName());

        Thread t3 = new Thread("MyName");
        Thread t4 = new Thread( () -> {
            System.out.println("Runnable...");

        });
        t3.start();
        t4.start();

        Thread t5 = new Thread( () -> {
            System.out.println("Runnable...." + Thread.currentThread().getName());
        },"RunnableThread");

        t5.start();

    }



}
