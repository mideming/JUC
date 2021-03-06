package com.mideming.juc.T05_lock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用reentrantLock可以进行“尝试锁定”tryLock，这样无法锁定，或者在指定时间内无法锁定，线程可以决定是否继续等待
 */
public class T03_TryLock {
    Lock lock = new ReentrantLock();
    void m1 () {
        try {
            lock.lock();
            for (int i = 0; i < 2; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    /**
     * 使用tryLock进行尝试锁定，不管锁定与否，方法都将继续执行
     * 可以根据tryLock的返回值来判定是否锁定
     * 也可以指定tryLock的时间，由于tryLock(time)抛出异常，所以要注意unlock的处理，必须放到finally中
     */
    void m2 () {
        boolean tryLock = false;
        try {
            tryLock = lock.tryLock(3, TimeUnit.SECONDS);
            if (tryLock) {
                System.out.println("拿到锁");
            } else {
                System.out.println("没拿到锁");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (tryLock) {
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) {
        T03_TryLock r1 = new T03_TryLock();
        new Thread(r1 :: m1).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r1::m2).start();
    }
}
