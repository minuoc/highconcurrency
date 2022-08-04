package ch8;

/**
 * @author chenlufeng
 * @date 2022/8/4
 */
public class DeadLock {
    private OtherService otherService;

    public void setOtherService(OtherService otherService) {
        this.otherService = otherService;
    }

    // DeadLock 的实例 的锁 - 资源A
    private final Object LOCK = new Object();

    public void m1() {
        synchronized (LOCK) {
            System.out.println("**************m1******************");
            otherService.s1();
        }
    }

    public void m2() {
        synchronized (LOCK) {
            System.out.println("*******************m2******************");
        }
    }
}
