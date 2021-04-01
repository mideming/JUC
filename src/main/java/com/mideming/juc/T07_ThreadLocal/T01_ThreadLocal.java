package com.mideming.juc.T07_ThreadLocal;


import java.util.concurrent.TimeUnit;

/**
 * 变量线程共享
 */
public class T01_ThreadLocal {
    static volatile Integer a;

    public static void main(String[] args) {
        new Thread(() -> {
            a = 2;
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程2---" + a);
        }).start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a = 1;
            System.out.println("线程1---" + a);
        }).start();
    }
}
