package imooc.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author chenlufeng
 * @date 2021/9/24
 */
public class AtomicArrayDemo {
    public static void main(String[] args) {

        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(1000);


    }





}

class Incrementer implements Runnable{

    private  AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(1000);

    public Incrementer(AtomicIntegerArray atomicIntegerArray) {
        this.atomicIntegerArray = atomicIntegerArray;
    }

    @Override
    public void run() {
        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            atomicIntegerArray.getAndIncrement(i);
        }

    }
}

class Decrementer implements  Runnable {
    private AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(1000);

    public Decrementer(AtomicIntegerArray atomicIntegerArray) {
        this.atomicIntegerArray = atomicIntegerArray;
    }

    @Override
    public void run() {
        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            atomicIntegerArray.decrementAndGet(i);
        }
    }
}
