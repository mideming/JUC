package com.mideming.juc.T02_sync;

/**
 * 对象加锁和类加锁的区别
 */
public class T05_ThreadSync {
    public static class MyThread1 implements Runnable {
        private String name;
        private T05_ThreadSync t05ThreadSync;
        public MyThread1(String name, T05_ThreadSync t05ThreadSync) {
            this.name = name;
            this.t05ThreadSync = t05ThreadSync;
        }
        @Override
        public void run() {
            t05ThreadSync.method1(name);
        }
    }
    public static class MyThread2 implements Runnable {
        private String name;
        private T05_ThreadSync t05ThreadSync;
        public MyThread2(String name, T05_ThreadSync t05ThreadSync) {
            this.name = name;
            this.t05ThreadSync = t05ThreadSync;
        }
        @Override
        public void run() {
            t05ThreadSync.method2(name);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println("对于对象锁，不同对象可以异步执行");
        T05_ThreadSync t05ThreadSync1 = new T05_ThreadSync();
        T05_ThreadSync t05ThreadSync2 = new T05_ThreadSync();
        Thread thread1 = new Thread(new MyThread1("thread1", t05ThreadSync1));
        Thread thread2 = new Thread(new MyThread1("thread2", t05ThreadSync2));
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("=========================");
        System.out.println("对于对象锁，相同对象同步执行");
        T05_ThreadSync t05ThreadSync3 = new T05_ThreadSync();
        Thread thread3 = new Thread(new MyThread1("thread3", t05ThreadSync3));
        Thread thread4 = new Thread(new MyThread1("thread4", t05ThreadSync3));
        thread3.start();
        thread4.start();
        thread3.join();
        thread4.join();
        System.out.println("=========================");
        System.out.println("对于类锁，不同对象同步执行");
        T05_ThreadSync t05ThreadSync5 = new T05_ThreadSync();
        T05_ThreadSync t05ThreadSync6 = new T05_ThreadSync();
        Thread thread5 = new Thread(new MyThread2("thread5", t05ThreadSync5));
        Thread thread6 = new Thread(new MyThread2("thread6", t05ThreadSync6));
        thread5.start();
        thread6.start();
        thread5.join();
        thread6.join();
        System.out.println("=========================");
        System.out.println("对于类锁，相同对象同步执行");
        T05_ThreadSync t05ThreadSync7 = new T05_ThreadSync();
        Thread thread7 = new Thread(new MyThread2("thread7", t05ThreadSync7));
        Thread thread8 = new Thread(new MyThread2("thread8", t05ThreadSync7));
        thread7.start();
        thread8.start();
        thread7.join();
        thread8.join();
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
