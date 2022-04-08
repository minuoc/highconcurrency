package core;

public class SynchronizedStaticTest {
    public static void main(String[] args) {
        final SynchronizedStaticTest synchronizedTest = new SynchronizedStaticTest();
        final SynchronizedStaticTest synchronizedTest2 = new SynchronizedStaticTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedTest.generalMethod1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedTest2.generalMethod2();
            }
        }).start();

    }


    private static synchronized void generalMethod1() {
        try{
            for (int i = 1; i < 3; i++) {
                System.out.println("generalMethod1 execute " + i + " time");
                Thread.sleep(300);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }


    private static synchronized void generalMethod2() {

        try{
            for (int i = 1; i < 3; i++) {
                System.out.println("generalMethod2 execute " + i + " time");
                Thread.sleep(300);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }


    }
}
