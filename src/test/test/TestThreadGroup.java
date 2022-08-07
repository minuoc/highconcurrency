package test;

public class TestThreadGroup {
    public static void main(String[] args) {
//        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();
//        ThreadGroup systemThreadGroup  = mainThreadGroup.getParent();
//        System.out.println("systemThreadGroup name = " + systemThreadGroup.getName());
//        System.out.println("mainThreadGroup name = " + mainThreadGroup.getName());


//        ThreadGroup subThreadGroup1 = new ThreadGroup("subThreadGroup1");
//        ThreadGroup subThreadGroup2 = new ThreadGroup(subThreadGroup1,"subThreadGroup2");
//        System.out.println("subThreadGroup1 parent name = " + subThreadGroup1.getParent().getName());
//        System.out.println("SubThreadGroup2 parent name = " + subThreadGroup2.getParent().getName());

        list();

    }

    public static void list(){
        ThreadGroup tg = new ThreadGroup("subgroup 1");
        Thread t1 = new Thread(tg,"thread 1");
        Thread t2 = new Thread(tg,"thread 2");
        Thread t3 = new Thread(tg,"thread 3");
        tg = new ThreadGroup(tg,"subgroup 2");

        Thread t4 = new Thread(tg,"my thread");
        tg = Thread.currentThread().getThreadGroup();
        int agc = tg.activeGroupCount();
        System.out.println("Active thread groups in " + tg.getName() + " thread group:" + agc);
        tg.list();


    }
}
