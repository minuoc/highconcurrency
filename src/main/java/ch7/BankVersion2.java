package ch7;

public class BankVersion2 {
    public static void main(String[] args) {
        BankRunnable bankRunnable = new BankRunnable();
        Thread window1 = new Thread(bankRunnable,"1");
        Thread window2 = new Thread(bankRunnable,"2");
        Thread window3 = new Thread(bankRunnable,"3");
        window1.start();
        window2.start();
        window3.start();

    }
}


