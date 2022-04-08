package imooc.queue;

import java.util.concurrent.BlockingQueue;

/**
 * @author chenlufeng
 * @date 2021/9/10
 */
public class Consumer implements Runnable{

    private BlockingQueue<Message> queue;

    public Consumer(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            Message msg;
            while(true){
                Thread.sleep(10);
                msg = queue.take();
                System.out.println("Consumed " + msg.getMsg());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
