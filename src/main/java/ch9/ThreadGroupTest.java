package ch9;

public class ThreadGroupTest {
    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("TG1");
        Thread thread = new Thread(threadGroup,"t1"){
            @Override
            public void run() {
                while(true){
                    try{
                        System.out.println(Thread.currentThread().getThreadGroup());
                        System.out.println(Thread.currentThread().getThreadGroup().getParent());
                        Thread.sleep(10_000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();

        ThreadGroup threadGroup2 = new ThreadGroup("TG2");
        
    }
}
