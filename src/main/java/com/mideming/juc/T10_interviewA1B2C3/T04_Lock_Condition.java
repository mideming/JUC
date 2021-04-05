package com.mideming.juc.T10_interviewA1B2C3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class T04_Lock_Condition {
    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();
        Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Thread t1 = new Thread( () -> {
            try {
                lock.lock();
                for (int i = 0; i < aI.length; i++) {
                    System.out.println(aI[i]);
                    condition2.signal();
                    condition1.await();
                }
                condition1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                lock.lock();
                for (int i = 0; i < aC.length; i++) {
                    condition2.await();
                    System.out.println(aC[i]);
                    condition1.signal();
                }
                condition2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        t1.start();
        t2.start();
    }
}
