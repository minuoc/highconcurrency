package test;

public class ThreadGroupExampleInterrupt {
    public static void main(String[] args) {
        MyThread mt = new MyThread();
        mt.setName("A");
        mt.start();

        mt = new MyThread();
        mt.setName("B");
        mt.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread.currentThread().getThreadGroup().interrupt();


    }

    public static class MyThread extends Thread {
        public void run() {
            synchronized ("A") {
                System.out.println(getName() + " aboutg to wait.");
                try {
                    "A".wait();
                } catch (InterruptedException e) {
                    System.out.println(getName() + " interrupted.");
                }
                System.out.println(getName() + " terminating.");

            }
        }
    }
}
