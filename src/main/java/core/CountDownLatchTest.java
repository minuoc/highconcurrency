package core;

/**
 * @author chenlufeng
 * @date 2021/9/13
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        CountDownLatchService service = new CountDownLatchService();
        for (int i = 0; i < 5; i++){
            MyCountDownLatchThread myThread = new MyCountDownLatchThread("thread"+(i+1),service);
            myThread.start();
        }
    }
}
