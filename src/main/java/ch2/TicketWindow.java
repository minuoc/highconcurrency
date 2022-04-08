package ch2;

public class TicketWindow extends Thread{
    private final int MAX = 50;
    private final String name;
    private int index = 1;

    public TicketWindow(String name){
        this.name = name;
    }
    public void run(){
        while(index <= MAX){
            try{
                Thread.sleep(1000L);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("当前柜台：" + name + ",当前的号码是：" + (index++) );
        }
    }
}
