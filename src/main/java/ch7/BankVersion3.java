package ch7;

public class BankVersion3 {
    public static void main(String[] args) {
        SynchronizedRunnable bankRunnable = new SynchronizedRunnable();
        Thread window1 = new Thread(bankRunnable,"1");
        Thread window2 = new Thread(bankRunnable,"2");
        Thread window3 = new Thread(bankRunnable,"3");
        window1.start();
        window2.start();
        window3.start();
    }
}
