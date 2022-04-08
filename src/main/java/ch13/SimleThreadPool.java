package ch13;

public class SimleThreadPool {
    private final int size;

    private final static int DEFAULT_SIZE = 10;

    public SimleThreadPool() {
        this(DEFAULT_SIZE);
    }

    public SimleThreadPool(int size) {
        this.size = size;
        init();
    }

    private void init() {
    }

    private enum TaskState {
        FREE,RUNING,BLOCKET,DEAD
    }
}
