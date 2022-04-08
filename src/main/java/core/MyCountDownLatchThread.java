package core;

/**
 * @author chenlufeng
 * @date 2021/9/13
 */
public class MyCountDownLatchThread extends Thread{
    CountDownLatchService countDownLatchService;

    public MyCountDownLatchThread(String name, CountDownLatchService countDownLatchService) {
        super();
        this.setName(name);
        this.countDownLatchService = countDownLatchService;
    }

    public void run(){
        this.countDownLatchService.doSomething();
    }
}
