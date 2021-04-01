package com.mideming.juc.T05_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock对于可重入锁的写法
 * 需要注意的是，必须要必须要必须要手动释放锁（重要的事情说三遍）
 * 使用syn锁定的话如果遇到异常，jvm会自动释放锁，但是lock必须手动释放锁，因此经常在finally中进行锁的释放
 */
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
