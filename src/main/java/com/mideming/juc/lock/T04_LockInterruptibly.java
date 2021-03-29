package com.mideming.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 需要注意的是，必须要必须要必须要手动释放锁（重要的事情说三遍）
 * 使用syn锁定的话如果遇到异常，jvm会自动释放锁，但是lock必须手动释放锁，因此经常在finally中进行锁的释放
 * 使用reentrantLock可以进行“尝试锁定”tryLock，这样无法锁定，或者在指定时间内无法锁定，线程可以决定是否继续等待
 */
public class T04_LockInterruptibly {
    Lock lock = new ReentrantLock();

    void m1() {
        try {
            lock.lock();
            System.out.println("m1拿到锁");
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 使用ReentrantLock还可以调用lockInterruptibly方法，可以对线程interrupt方法做出响应，
     * 在一个线程等待锁的过程中，可以被打断
     */
    void m2() {
        try {
            lock.lockInterruptibly();
            System.out.println("m2");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        T04_LockInterruptibly r1 = new T04_LockInterruptibly();
        new Thread(r1::m1).start();
        Thread thread = new Thread(r1::m2);
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
