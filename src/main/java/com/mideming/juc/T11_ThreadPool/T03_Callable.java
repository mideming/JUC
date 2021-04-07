package com.mideming.juc.T11_ThreadPool;

import java.util.concurrent.*;

public class T03_Callable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> c = new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.SECONDS.sleep(3);
                return "hello callable";
            }
        };
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 异步
        Future<String> future = executorService.submit(c);
        System.out.println("aaa");
        // 阻塞
        System.out.println(future.get());
        System.out.println("bbb");
        executorService.shutdown();
    }
}
