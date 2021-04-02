package com.mideming.juc.T05_lock;

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
                System.out.println("T1 running start...");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // permits++
                System.out.println("T1 running end...");
                s.release();
            }
        }).start();
        new Thread(() -> {
            try {
                s.acquire();
                System.out.println("T2 running start...");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("T2 running end...");
                s.release();
            }
        }).start();
        new Thread(() -> {
            try {
                s.acquire();
                System.out.println("T3 running start...");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("T3 running end...");
                s.release();
            }
        }).start();
        new Thread(() -> {
            try {
                s.acquire();
                System.out.println("T4 running start...");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("T4 running end...");
                s.release();
            }
        }).start();
    }
}
