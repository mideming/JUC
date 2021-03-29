package com.mideming.juc.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * Synchronized使用了锁
 * AtomicLong没有使用锁，使用了CAS
 * LongAdder使用了分段锁
 */
public class T_LongAdder {
    static long count1 = 0;
    static AtomicLong count2 = new AtomicLong(0);
    static LongAdder count3 = new LongAdder();

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        Object o = new Object();
        for (int i = 0; i < 1000; i++) {
            threads.add(new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    synchronized (o) {
                        count1++;
                    }
                }
            }));
        }
        long start1 = System.currentTimeMillis();
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
        long end1 = System.currentTimeMillis();
        System.out.println("count1=" + count1 + "用时" + (end1 - start1));
        List<Thread> threads2 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            threads2.add(new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    count2.incrementAndGet();
                }
            }));
        }
        long start2 = System.currentTimeMillis();
        for (Thread thread : threads2) {
            thread.start();
        }
        for (Thread thread : threads2) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end2 = System.currentTimeMillis();
        System.out.println("count2=" + count2 + "用时" + (end2 - start2));
        List<Thread> threads3 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            threads3.add(new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    count3.increment();
                }
            }));
        }
        long start3 = System.currentTimeMillis();
        for (Thread thread : threads3) {
            thread.start();
        }
        for (Thread thread : threads3) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end3 = System.currentTimeMillis();
        System.out.println("count3=" + count3 + "用时" + (end3 - start3));

    }
}
