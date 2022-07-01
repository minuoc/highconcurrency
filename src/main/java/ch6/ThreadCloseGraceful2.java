package ch6;

public class ThreadCloseGraceful2 {
    private static class WorkerThread extends Thread {
        public void run(){
            while(true){
                System.out.println("running");
                if(Thread.interrupted()){
                    //收到终端之后 跳出循环 线程退出
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
