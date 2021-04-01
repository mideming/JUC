package com.mideming.juc.T02_sync;

/**
 * 对某个类加锁
 */
public class T04_Sync {
    private static int i = 0;
    // 这里等同于synchronized (Sync4.class)
    public synchronized static void test () {
        i++;
    }

    public static void test2 () {
        synchronized (T04_Sync.class) {
            i++;
        }
    }
    public static void main(String[] args) {
        T04_Sync t4 = new T04_Sync();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                t4.test();
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t4.i);
    }
}
