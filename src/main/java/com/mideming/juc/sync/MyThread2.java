package com.mideming.juc.sync;

public class MyThread2 implements Runnable {
    private String name;
    private ThreadSync threadSync;
    public MyThread2(String name, ThreadSync threadSync) {
        this.name = name;
        this.threadSync = threadSync;
    }
        @Override
        public void run() {
            threadSync.method2(name);
        }
}
