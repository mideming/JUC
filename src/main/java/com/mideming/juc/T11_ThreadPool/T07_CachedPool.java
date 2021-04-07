package com.mideming.juc.T11_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T07_CachedPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 2; i++) {
            executorService.execute(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        System.out.println(executorService);
        try {
            TimeUnit.SECONDS.sleep(80);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(executorService);
    }
}
