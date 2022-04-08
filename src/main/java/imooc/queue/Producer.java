package imooc.queue;

import lombok.Data;
import lombok.Getter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chenlufeng
 * @date 2021/9/10
 */

@Data
public class Producer implements Runnable{

    private AtomicInteger i = new AtomicInteger(0);

    private BlockingQueue<Message> queue;

    public Producer(BlockingQueue<Message> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

            Message msg = new Message("" + i.incrementAndGet());
            try {
                Thread.sleep(100);
                queue.put(msg);
                System.out.println("Produced "+msg.getMsg());
            } catch (InterruptedException e){
                e.printStackTrace();
            }



    }
}
