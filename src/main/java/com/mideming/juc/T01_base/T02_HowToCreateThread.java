package com.mideming.juc.T01_base;

/**
 * 启动线程的3种方式：
 * 1.继承Thread
 * 2.实现Runnable
 * 3.Executors.newCachedThread
 */
public class T02_HowToCreateThread {
    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello MyThread");
        }
    }
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello MyRunnable");
        }
    }

    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new MyRunnable()).start();
        new Thread(() -> {
            System.out.println("Hello Lambda");
        }).start();
    }
}
