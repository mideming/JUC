package com.mideming.juc.T04_atomic;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * Synchronized使用了锁
 * AtomicInteger没有使用锁，使用了CAS
 */
public class T_AtomicInteger {
    static AtomicInteger a = new AtomicInteger(0);
    public void test() {
        for (int i = 0; i < 10000; i++) {
            a.incrementAndGet();
        }
    }
    public static void main(String[] args) {
        T_AtomicInteger t2 = new T_AtomicInteger();
        for (int i = 0; i < 10; i++) {
            new Thread(t2::test).start();
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a);
    }
}
