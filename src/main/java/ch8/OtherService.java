package ch8;

/**
 * @author chenlufeng
 * @date 2022/8/4
 */
public class OtherService {

    private DeadLock deadLock;

    public void setDeadLock(DeadLock deadLock) {
        this.deadLock = deadLock;
    }

    // OtherService 的实例的锁 - 资源B
    private final Object LOCK = new Object();



    public void s1() {
        synchronized (LOCK) {
            System.out.println("--------------s1----------------");
        }
    }

    public void s2() {
        synchronized (LOCK) {
            System.out.println("----------------s2--------------");
            deadLock.m2();
        }
    }

}
