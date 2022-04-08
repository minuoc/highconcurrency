package ch6;

public class MayNotBeTerminatedDemo {
    public static void main(String[] args) throws InterruptedException {
        TaskRunner taskRunner = new TaskRunner();
        taskRunner.init();

        taskRunner.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("before doing task");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {

                }
                System.out.println("after doing task");
            }
        });
        taskRunner.workerThread.interrupt();
    }
}
