package com.mideming.juc.T11_ThreadPool;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class T04_HelloThreadPool {
    static class Task implements Runnable {
        private int i;

        public Task(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Task " + i);
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Task{" +
                    "i=" + i +
                    '}';
        }
    }

    public static void main(String[] args) {
        // 核心线程2个，最大线程4个，最多任务4个
        // 当超过2个任务，塞进任务阻塞队列中，超过6个，新增线程直到最大线程数为4.再超过就按照拒绝策略
        // 线程空闲超过规定时间，就回收给os，只留2个线程就可
        // 一般都自定义拒绝策略，写入kafka或者redis，等线程闲下来继续处理
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 4,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(4),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy());// 抛异常
                new ThreadPoolExecutor.DiscardPolicy());// 悄悄的扔掉，不管
//                new ThreadPoolExecutor.DiscardOldestPolicy());// 抛弃最先进来的的任务
//                new ThreadPoolExecutor.CallerRunsPolicy());// 由当前线程执行

        for (int i = 0; i < 8; i++) {
            tpe.execute(new Task(i));
        }

        System.out.println(tpe.getQueue());

        tpe.execute(new Task(100));

        System.out.println(tpe.getQueue());

        tpe.shutdown();
    }

}
