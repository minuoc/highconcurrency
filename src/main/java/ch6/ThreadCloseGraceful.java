package ch6;

public class ThreadCloseGraceful {
    private static class WorkThread extends Thread {
        private volatile boolean flag = true;
        public void run(){
            while(flag){

            }
        }

        public void shutdown(){
            this.flag = false;
        }


    }

    public static void main(String[] args) {
        WorkThread workThread = new WorkThread();
        workThread.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        workThread.shutdown();
    }

}
