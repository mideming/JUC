package com.mideming.juc.T10_interviewA1B2C3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class T03_Lock_Condition {
    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Thread t1 = new Thread( () -> {
            lock.lock();
            for (int i = 0; i < aI.length; i++) {
                System.out.println(aI[i]);
                condition.signal();
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            condition.signal();
        });
        Thread t2 = new Thread(() -> {
            lock.lock();
            for (int i = 0; i < aC.length; i++) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(aC[i]);
                condition.signal();
            }
            condition.signal();
        });
        t1.start();
        t2.start();
    }
}
