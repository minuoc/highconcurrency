package ch3;

public class CreateThread2 {
    public static void main(String[] args) {
        Thread t = new Thread(){
            public void  run(){
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
        t.setName("t1");

//        System.out.println(t.getThreadGroup());
//        System.out.println(Thread.currentThread().getName());

        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        System.out.println(threadGroup.getName());
        System.out.println(threadGroup.activeCount());
        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        for (Thread temp : threads){
            System.out.println(temp.getName());
        }
        System.out.println(  );
    }

}
