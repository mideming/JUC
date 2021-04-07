package com.mideming.juc.T11_ThreadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class T06_Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> ft = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(5);
            return "hello future";
        });
        new Thread(ft).start();
        System.out.println("aaa");
        // 阻塞
        System.out.println(ft.get());
        System.out.println("bbb");
    }
}
