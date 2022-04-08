package ch6;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TaskRunner {
    protected final BlockingQueue<Runnable> channel;
    protected volatile Thread workerThread;

    public TaskRunner(BlockingQueue<Runnable> channel) {
        this.channel = channel;
        this.workerThread = new WorkerThread();
    }

    public TaskRunner(){
        this(new LinkedBlockingQueue<>());
    }

    public void init(){
        final Thread t = workerThread;
    }

    public void submit(Runnable task) throws InterruptedException{
        channel.put(task);
    }

    private class WorkerThread extends Thread {

        public void run() {
            Runnable task = null;
            try {
                for(;;){
                    task = channel.take();
                    try{
                        task.run();
                    }catch (Throwable e){
                        e.printStackTrace();
                    }
                }
            } catch (InterruptedException e) {

            }
        }

    }
}
