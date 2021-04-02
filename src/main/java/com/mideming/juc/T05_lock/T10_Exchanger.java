package com.mideming.juc.T05_lock;

import java.util.concurrent.Exchanger;

/**
 * 2个线程交换数据
 */
public class T10_Exchanger {
    static Exchanger<String> exchanger = new Exchanger<>();
    public static void main(String[] args) {
        new Thread(() -> {
            String t1 = "T1";
            try {
                // 阻塞
                t1 = exchanger.exchange(t1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "===" + t1);
        },"t1").start();
        new Thread(() -> {
            String t2 = "T2";
            try {
                t2 = exchanger.exchange(t2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "===" + t2);
        },"t2").start();
    }
}
