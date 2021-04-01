package com.mideming.juc.T01_base;

import java.util.concurrent.TimeUnit;

/**
 * 线程的基本理解
 */
public class T01_WhatIsThread {
    private static class T1 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "T1");
            }
        }
    }
    private static class T2 extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "T2");
            }
        }
    }

    public static void main(String[] args) {
        // 相当于同步调用一个方法
        new T1().run();
        // 异步调用T1的run方法
        new T1().start();
        new T2().start();
    }
}
