package com.mideming.juc.interview01;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 实现一个容器，提供2个方法，add,size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 *
 * 通过countDownLatch
 */
public class T05_CountDownLatch {
    List lists = new LinkedList();
    CountDownLatch latch = new CountDownLatch(1);
    public void add(Object o) {
        lists.add(o);
    }
    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        T05_CountDownLatch c = new T05_CountDownLatch();
        new Thread(() -> {
            System.out.println("T2启动");
            if (c.size() != 5) {
                try {
                    c.latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("T2结束");
        }).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            System.out.println("T1启动");
            for (int i = 0; i <10; i++) {
                if(i == 5) {
                    c.latch.countDown();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                c.add(new Object());
                System.out.println("add" + i);

            }
            System.out.println("T1结束");
        }).start();

    }
}
