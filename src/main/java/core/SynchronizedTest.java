package core;

public class SynchronizedTest {
    String lockA = "lockA";
    public static void main(String[] args) {

        final SynchronizedTest synchronizedTest = new SynchronizedTest();
//        final SynchronizedTest synchronizedTest2 = new SynchronizedTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedTest.generalMethod1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedTest.generalMethod2();
            }
        }).start();

    }


    private  void generalMethod1() {
        try{
            synchronized (lockA){
                for (int i = 1; i < 3; i++) {
                    System.out.println("generalMethod1 execute " + i + " time");
                    Thread.sleep(300);
                }
            }

        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }


    private  void generalMethod2() {

        try{
            synchronized (lockA){
                for (int i = 1; i < 3; i++) {
                    System.out.println("generalMethod2 execute " + i + " time");
                    Thread.sleep(300);
                }
            }

        }catch (InterruptedException e){
            e.printStackTrace();
        }


    }
}
