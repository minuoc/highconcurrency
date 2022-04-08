package core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenlufeng
 * @date 2021/9/16
 */
public class ConditionUseCase {

    /**
     * 创建锁
     * @param args
     */
    public Lock readLock = new ReentrantLock();

    /**
     * 创建条件
     * @param args
     */
    public Condition condition = readLock.newCondition();

    public static void main(String[] args) {
        ConditionUseCase useCase = new ConditionUseCase();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(()->{
            useCase.conditionWait();
        });
        executorService.execute(()->{
            useCase.conditionSignal();
        });




    }

    public void conditionWait() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "拿到了锁");
            System.out.println(Thread.currentThread().getName() + "等待信号");
            condition.await();
            System.out.println(Thread.currentThread().getName() + "拿到信号");
        } catch ( Exception e){

        } finally {
            readLock.unlock();
        }
    }

    public void conditionSignal(){
        readLock.lock();
        try {
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + "拿到锁了");
            condition.signal();
            System.out.println(Thread.currentThread().getName() + "发出信号");

        }catch (Exception e) {

        }finally {
            readLock.unlock();
        }
    }
}
