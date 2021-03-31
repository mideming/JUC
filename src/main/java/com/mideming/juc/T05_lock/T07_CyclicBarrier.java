package com.mideming.juc.T05_lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 复杂操作
 * - 数据库
 * - 网络
 * - 文件
 * 并发执行,必须等上面3个线程都执行完
 */
public class T07_CyclicBarrier {
    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(20, () -> {
            System.out.println("满人,发车");
        });
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName());
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
