package com.mideming.juc.T06_interview.interview01;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * 实现一个容器，提供2个方法，add,size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 *
 *
 */
public class T05_LockSupportNoSleep {
    volatile List lists = new LinkedList();
    public void add(Object o) {
        lists.add(o);
    }
    public int size() {
        return lists.size();
    }
    static Thread t1;
    static Thread t2;
    public static void main(String[] args) {
        T05_LockSupportNoSleep c = new T05_LockSupportNoSleep();
        t2 = new Thread(() -> {
            System.out.println("T2启动");
            LockSupport.park();
            System.out.println("T2结束");
            LockSupport.unpark(t1);
        });
        t1 = new Thread(() -> {
            System.out.println("T1启动");
            for (int i = 0; i <10; i++) {
                if(i == 5) {
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
                c.add(new Object());
                System.out.println("add" + i);

            }
            System.out.println("T1结束");
        });

        t2.start();
        t1.start();
    }
}
