package com.mideming.juc.interview01;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
/**
 * 实现一个容器，提供2个方法，add,size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 * 给list添加volatile之后，t2能够接到通知，但是t2线程的死循环浪费cpu，如果不用死循环怎么做？
 * 这里使用wait和notify，wait会释放锁，但notify不会释放
 * 运用这种方法，必须要保证t2先执行，也就是首先让t2监听才可以
 * 下面的程序输出结果并不是size=5时t2退出，而是t1结束时t2才接收到通知而退出，想想这是为什么？
 * notify之后，t1没有释放锁
 */
public class T03_NotifyHoldingLock {
    volatile List lists = new LinkedList();
    public void add(Object o) {
        lists.add(o);
    }
    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        T03_NotifyHoldingLock c = new T03_NotifyHoldingLock();
        Object lock = new Object();
        new Thread(() -> {
            synchronized (lock) {
                if (c.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("线程2结束");
            }
        }).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i <10; i++) {
                    if(i == 5) {
                        // notify不能释放锁
                        lock.notify();
                    }
                    c.add(new Object());
                    System.out.println("add" + i);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
