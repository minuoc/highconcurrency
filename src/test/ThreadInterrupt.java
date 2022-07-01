package test;

public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(){
            public void run(){
                while(true){

                }
            }
        };


        t.start();
        Thread.sleep(100); //sleep 将 interrupted 设置为了 false
        System.out.println(t.isInterrupted());
        t.interrupt();
        System.out.println(t.isInterrupted());
    }
}
