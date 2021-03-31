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
}
