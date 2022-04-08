package ch2;

public class Bank {
    public static void main(String[] args) {
        TicketWindow t1 = new TicketWindow("1号柜台");
        TicketWindow t2 = new TicketWindow("2号柜台");
        TicketWindow t3 = new TicketWindow("3号柜台");
        TicketWindow t4 = new TicketWindow("4号柜台");
        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
}
