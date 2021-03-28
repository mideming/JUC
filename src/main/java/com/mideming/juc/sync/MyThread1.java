package com.mideming.juc.sync;

public class MyThread1 implements Runnable {
    private String name;
    private ThreadSync threadSync;
    public MyThread1(String name, ThreadSync threadSync) {
        this.name = name;
        this.threadSync = threadSync;
    }
        @Override
        public void run() {
            threadSync.method1(name);
        }
}
