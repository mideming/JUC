package com.mideming.juc.lock;

import java.util.concurrent.Semaphore;

/**
 * 信号灯
 * 限流：最多允许你有几个线程同时运行
 */
public class T09_Semaphore {
    public static void main(String[] args) {
        Semaphore s = new Semaphore(2);
        new Thread(() -> {
            try {
                // permits--
                s.acquire();
                System.out.println("T1 running...");
                Thread.sleep(200);
                System.out.println("T1 running...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // permits++
                s.release();
            }
        }).start();
        new Thread(() -> {
            try {
                s.acquire();
                System.out.println("T2 running...");
                Thread.sleep(200);
                System.out.println("T2 running...");
                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
