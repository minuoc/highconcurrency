package ch9;

/**
 * @author chenlufeng
 * @date 2022/8/4
 */
public class ProduceConsumerVersion1 {

    private int i = 1;
    private final Object LOCK = new Object();

    private void produce(){
        synchronized (LOCK) {
            System.out.println("P->" + i++);
        }
    }


    private void consume(){
        synchronized (LOCK) {
            System.out.println("C->" + (i));
        }
    }

    public static void main(String[] args) {
        ProduceConsumerVersion1 pc = new ProduceConsumerVersion1();

        new Thread(() -> {
            while (true) {
                pc.produce();
            }
        },"P").start();

        new Thread(()-> {
            while(true) {
                pc.consume();
            }
        },"C").start();

    }

}
