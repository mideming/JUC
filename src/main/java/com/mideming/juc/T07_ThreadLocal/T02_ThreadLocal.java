package com.mideming.juc.T07_ThreadLocal;


import java.util.concurrent.TimeUnit;

/**
 * 变量线程独享，通过Thread的ThreadLocalMap对象存储
 */
public class T02_ThreadLocal {
    static ThreadLocal<Integer> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            tl.set(2);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程2---" + tl.get());
        }).start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tl.set(1);
            System.out.println("线程1---" + tl.get());
        }).start();
    }
}
