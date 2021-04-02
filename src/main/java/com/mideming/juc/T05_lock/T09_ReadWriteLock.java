package com.mideming.juc.T05_lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * 共享锁（读），读的时候加锁，允许其他人读，但不允许写
 * 排他锁（写），写的时候加锁，不允许其他人读，也不允许其他人写
 */
public class T09_ReadWriteLock {
    // 这是排他锁
    static Lock lock = new ReentrantLock();
    private static int value = 0;
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    // 这是共享锁
    static Lock readLock = readWriteLock.readLock();
    // 这是排他锁
    static Lock writeLock = readWriteLock.writeLock();
    public static int read(Lock lock) {
        int result = 0;
        try {
            lock.lock();
            Thread.sleep(1000);
            result = value;
            System.out.println("读到的数字" + value);
            System.out.println("read over");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return result;
    }
    public static void write(Lock lock, int a) {
        try {
            lock.lock();
            Thread.sleep(1000);
            value = a;
            System.out.println("write over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Runnable rRead = () -> {
            read(lock);
        };
        Runnable rWrite = () -> {
            write(lock, new Random().nextInt());
        };
//        Runnable rRead = () -> {
//            read(readLock);
//        };
//        Runnable rWrite = () -> {
//            write(writeLock, new Random().nextInt());
//        };
        for (int i = 0; i < 18; i++) {
            new Thread(rRead).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(rWrite).start();
        }
    }
}
