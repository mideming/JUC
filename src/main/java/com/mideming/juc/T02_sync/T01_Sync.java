package com.mideming.juc.T02_sync;

/**
 * 对某个对象加锁
 */
public class T01_Sync {
    int i = 0;
    Object o = new Object();
    public void test () {
        // 任何线程执行下面的代码，必须要拿到o的锁
        synchronized (o) {
            i++;
        }
    }

    public static void main(String[] args) {
        T01_Sync t1 = new T01_Sync();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                t1.test();
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t1.i);
    }
}
