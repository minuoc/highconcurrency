package imooc.queue;

import java.util.concurrent.BlockingDeque;

/**
 * @author chenlufeng
 * @date 2021/9/8
 */
public class ArrayBlockingQueueDemo {

    public static void main(String[] args) {


    }


}

class Interviewer implements Runnable {

    BlockingDeque<String> queue;

    public Interviewer(BlockingDeque<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            String candidate = "Candidate" + i;

        }

    }
}
