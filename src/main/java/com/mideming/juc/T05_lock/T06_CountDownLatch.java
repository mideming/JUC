package com.mideming.juc.T05_lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class T06_CountDownLatch {
    public static void main(String[] args) {
//        usingCountDownLatch();
        usingJoin();
    }

    private static void usingCountDownLatch() {
        CountDownLatch latch = new CountDownLatch(100);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    System.out.println(j);
                }
                latch.countDown();
            }));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("latch结束");
    }
    private static void usingJoin() {
        CountDownLatch latch = new CountDownLatch(100);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    System.out.println(j);
                }
            }));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("join结束");
    }
}
