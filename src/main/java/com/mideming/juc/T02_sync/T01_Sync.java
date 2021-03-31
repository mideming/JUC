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
}
