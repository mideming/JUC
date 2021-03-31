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
}
