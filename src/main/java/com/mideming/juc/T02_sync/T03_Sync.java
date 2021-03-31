package com.mideming.juc.T02_sync;

/**
 * 对某个对象加锁
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
}
