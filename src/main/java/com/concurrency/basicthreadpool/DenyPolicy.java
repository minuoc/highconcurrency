package com.concurrency.basicthreadpool;

/**
 * @author chenlufeng
 * @date 2022/6/15
 */
public interface DenyPolicy {


    void reject(Runnable runnable,ThreadPool threadPool);


    class DiscardDenyPolicy implements DenyPolicy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {

        }
    }


    class AbortDenyPolicy implements DenyPolicy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            throw new RunnableDenyException();
        }
    }

    class RunnerDenyPolicy implements DenyPolicy {

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            if (!threadPool.isShutdown()) {
                runnable.run();
            }
        }
    }
}
