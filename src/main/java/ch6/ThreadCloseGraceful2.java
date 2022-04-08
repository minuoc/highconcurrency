package ch6;

public class ThreadCloseGraceful2 {
    private static class WorkerThread extends Thread {
        public void run(){
            while(true){
                if(Thread.interrupted()){
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        WorkerThread workerThread = new WorkerThread();
        workerThread.start();
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        workerThread.interrupt();
    }

}
