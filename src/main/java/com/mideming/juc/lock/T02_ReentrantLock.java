package com.mideming.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T02_ReentrantLock {
    Lock lock = new ReentrantLock();
    void m1 () {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                System.out.println(i);
                if (i == 2) {
                    m2();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void m2 () {
        try {
            lock.lock();
            System.out.println("m2...");

        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        T02_ReentrantLock r1 = new T02_ReentrantLock();
        new Thread(r1 :: m1).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r1::m2).start();
    }
}
