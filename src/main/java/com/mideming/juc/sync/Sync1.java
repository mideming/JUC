package com.mideming.juc.sync;

/**
 * 对某个对象加锁
 */
public class Sync1 {
    int i = 0;
    Object o = new Object();
    public void test () {
        // 任何线程执行下面的代码，必须要拿到o的锁
        synchronized (o) {
            i++;
        }
    }
}
