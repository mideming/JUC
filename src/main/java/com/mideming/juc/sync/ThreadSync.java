package com.mideming.juc.sync;

/**
 * 对象加锁和类加锁的区别
 */
public class ThreadSync {

    public static void main(String[] args) throws InterruptedException {
        ThreadSync threadSync1 = new ThreadSync();
        ThreadSync threadSync2 = new ThreadSync();
        Thread thread1 = new Thread(new MyThread1("thread1", threadSync1));
//        Thread thread2 = new Thread(new MyThread1("thread2", threadSync1));
        Thread thread2 = new Thread(new MyThread1("thread2", threadSync2));
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("=========================");
        ThreadSync threadSync3 = new ThreadSync();
        ThreadSync threadSync4 = new ThreadSync();
        Thread thread3 = new Thread(new MyThread2("thread3", threadSync3));
        Thread thread4 = new Thread(new MyThread2("thread4", threadSync4));
        thread3.start();
        thread4.start();
    }

    public synchronized void method1(String threadName) {
        for (int i = 0; i < 10; i++) {
            System.out.println(threadName + "---" + i);
        }
    }

    public synchronized static void method2(String threadName) {
        for (int i = 0; i < 10; i++) {
            System.out.println(threadName + "---" + i);
        }
    }
}
