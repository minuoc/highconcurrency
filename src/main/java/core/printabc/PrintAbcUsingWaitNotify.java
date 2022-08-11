package core.printabc;

public class PrintAbcUsingWaitNotify {
    private int state;
    private int times;
    private static final Object LOCK = new Object();

    public PrintAbcUsingWaitNotify(int times) {
        this.times = times;
    }




    private void printLetter(String name,int targetState) {
        for (int i = 0; i < times; i++) {
            synchronized (LOCK) {
                while (state % 3 != targetState){
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                state ++;
                System.out.println(name);
                LOCK.notifyAll();
            }

        }
    }


    public static void main(String[] args) {
        PrintAbcUsingWaitNotify loopThread = new PrintAbcUsingWaitNotify(10);
        new Thread(()->{
            loopThread.printLetter("A",0);
        },"A").start();

        new Thread(()-> {
            loopThread.printLetter("B",1);
        },"B").start();


        new Thread(()-> {
            loopThread.printLetter("C",2);
        },"C").start();

    }


}
