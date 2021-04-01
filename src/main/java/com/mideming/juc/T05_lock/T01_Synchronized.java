package com.mideming.juc.T05_lock;

/**
 * synchronized对于可重入锁的写法
 */
public class T01_Synchronized {
    synchronized void m1 () {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            if (i == 2) {
                m2();
            }
        }
    }

    synchronized void m2 () {
        System.out.println("m2...");
    }

    public static void main(String[] args) {
        T01_Synchronized r1 = new T01_Synchronized();
        new Thread(r1 :: m1).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r1::m2).start();
    }
}
