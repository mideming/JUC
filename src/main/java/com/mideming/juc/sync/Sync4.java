package com.mideming.juc.sync;

/**
 * 对某个类加锁
 */
public class Sync4 {
    private static int i = 0;
    // 这里等同于synchronized (Sync4.class)
    public synchronized static void test () {
        i++;
    }

    public static void test2 () {
        synchronized (Sync4.class) {
            i++;
        }
    }
}
