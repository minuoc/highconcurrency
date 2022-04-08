package ch6;

public class ThreadService {
    private Thread executeThread;
    private boolean finished = false;

    public void execute(Runnable task){
       executeThread = new Thread(){
         public void run(){
             Thread runner = new Thread(task);
             runner.setDaemon(true);
             runner.start();
             try{
                 runner.join();
                 finished = true;
             }catch (InterruptedException e){

             }
         }

       };
       executeThread.start();
    }

//    public void shutdown


}
