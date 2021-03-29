package com.mideming.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock还可以指定为公平锁
 */
public class T05_LockFair {
    static Lock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + "获得锁");
                } finally {
                    lock.unlock();
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + "获得锁");
                } finally {
                    lock.unlock();
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}
