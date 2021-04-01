package com.mideming.juc.T02_sync;

/**
 * 对某个对象加锁
 */
public class T02_Sync {
    int i = 0;

    public void test() {
        //任何线程要执行下面代码，需要拿到this的锁
        synchronized (this) {
            i++;
        }
    }
    public static void main(String[] args) {
        T02_Sync t2 = new T02_Sync();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                t2.test();
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t2.i);
    }
}
