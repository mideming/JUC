package com.mideming.juc.atomic;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class T_AtomicInteger {
    static AtomicInteger a = new AtomicInteger(0);

    public void test() {
        for (int i = 0; i < 10000; i++) {
            // a++;
            a.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        T_AtomicInteger t2 = new T_AtomicInteger();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t2::test));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        threads.forEach((t) -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(a);
    }
}
