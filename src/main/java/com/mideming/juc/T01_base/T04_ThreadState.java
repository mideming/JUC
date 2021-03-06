package com.mideming.juc.T01_base;

/**
 * 线程的状态
 */
public class T04_ThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("A" + i);
            }
        });
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        thread.join();
        System.out.println(thread.getState());
    }
}
