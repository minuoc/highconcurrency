package core;

public class MyThread extends Thread{
    private SemaphoreService service;

    public MyThread(String name, SemaphoreService service){
        super();
        this.setName(name);
        this.service = service;
    }


    public void run(){
        this.service.doSomething();
    }

}
