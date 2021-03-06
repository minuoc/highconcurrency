package imooc.cas;

/**
 * @author chenlufeng
 * @date 2021/9/25
 */
public class TwoThreadCompetition implements Runnable{
    private volatile int value;

    /**
     *
     * @param expectedValue 期望值
     * @param newValue 赋予的新值
     * @return
     */
    public synchronized boolean compareAndSwap(int expectedValue,int newValue){
        int oldValue = value;
        if(oldValue == expectedValue) {
            value = newValue;
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        compareAndSwap(0,1);
    }


    public static void main(String[] args) throws InterruptedException {
        TwoThreadCompetition r = new TwoThreadCompetition();
        r.value = 0;
        Thread t1 = new Thread(r,"Thread 1");
        Thread t2 = new Thread(r,"Thread 2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(r.value);

    }
}
