package core;

public class SemaphoreTest {
    public static void main(String[] args) {
        SemaphoreService service = new SemaphoreService();
        for (int i = 0; i < 10; i++){
            MyThread myThread = new MyThread("thread"+(i+1),service);
            myThread.start();
        }
    }
}
