package com.mideming.juc.T03_volatileTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * volatile不能保证多个线程共同修改变量时所带来的不一致问题，也就是说volatile不能替代synchronized
 */
public class T2 {

    static volatile int a = 0;

//    public void test() {
    public synchronized void test() {
        for (int i = 0; i < 10000; i++) {
            a++;
        }
    }

    public static void main(String[] args) {
        T2 t2 = new T2();
        List<Thread> threads = new ArrayList<>();
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
