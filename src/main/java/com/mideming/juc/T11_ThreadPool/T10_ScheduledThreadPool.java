package com.mideming.juc.T11_ThreadPool;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class T10_ScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);
        // initialDelay 第一个任务执行时间
        // period 每个任务执行时间间隔
        executorService.scheduleAtFixedRate(() -> {
//            try {
//                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            System.out.println(Thread.currentThread().getName());
        },0,500, TimeUnit.MILLISECONDS);
    }
}
