package com.mideming.juc.T11_ThreadPool;

import java.util.concurrent.*;

public class T11_MyRejectHandler {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(4, 4, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(6), Executors.defaultThreadFactory(), new MyHandler());

    }

    static class MyHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            // log("r rejected");
            // save r to kafka/mysql/redis
            // try 3 times
            if (executor.getQueue().size() < 10000) {
                // try put again;
            }
        }
    }
}
