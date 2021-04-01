package com.mideming.juc.T03_volatileTest;

/**
 * new对象分为3个过程
 * 1.申请一块内存，给默认值。a=0
 * 2.赋值。a=8
 * 3.把值给到INSTANCE
 * 存在指令重排序，2和3换了位置，导致把a=0给到了对象，其他线程进来判断INSTANCE不为空，直接拿走INSTANCE，此时INSTANCE的a=0.
 * volatile可以禁止指令重排序
 */
public class Singleton {
    private static volatile Singleton INSTANCE;
    private Singleton () {}
    public static Singleton getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton.class) {
                if (INSTANCE == null) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;
    }
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Singleton.getInstance().hashCode());
            }).start();
        }
    }
}
