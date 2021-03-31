package com.mideming.juc.T03_volatileTest;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile不能保证多个线程共同修改变量时所带来的不一致问题，也就是说volatile不能替代synchronized
 */
public class T2 {

    static volatile int a = 0;

    public void test() {
        for (int i = 0; i < 10000; i++) {
//            synchronized (T2.class) {
                a++;
//            }
        }
    }

    public static void main(String[] args) {
        T2 t2 = new T2();
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
