package com.mideming.juc.T02_sync;

/**
 * 对某个方法加锁
 */
public class T03_Sync {
    int i = 0;
    // 等同于在方法的代码执行时要synchronized(this)
    public synchronized void test () {
        i++;
    }
    // 访问这个方法不需要上锁
    public void test2 () {

    }
    public static void main(String[] args) {
        T03_Sync t3 = new T03_Sync();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                t3.test();
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t3.i);
    }
}
