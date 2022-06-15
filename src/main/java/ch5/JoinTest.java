package ch5;

public class JoinTest {
    public static void main(String[] args) {
        Thread boyThread = new BoyThread();
        boyThread.start();


    }

    static class BoyThread extends Thread {
        public void run(){
            System.out.println("男孩女孩准备出去逛街");
            Thread girlThread = new GirlThread();
            girlThread.start();
            try {
                girlThread.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("男孩女孩出发逛街 ");
        }

    }

    static class GirlThread extends Thread {
        public void run(){
            System.out.println("女孩准备化妆，男孩等待");
            try {
                Thread.sleep(2_000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("女孩化妆完成");
        }
    }
}
