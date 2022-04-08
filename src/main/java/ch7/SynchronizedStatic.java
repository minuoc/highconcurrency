package ch7;

public class SynchronizedStatic {

    static {
        synchronized (SynchronizedStatic.class){
            System.out.println("static " + Thread.currentThread().getName());
            try {
                Thread.sleep(10_100);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

    }
}
