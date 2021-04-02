package com.mideming.juc.T06_interview.interview02;

import java.util.LinkedList;

/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 * 使用wait和notifyAll来实现
 */
public class MyContainer1<T> {
    private LinkedList<T> list = new LinkedList<>();
    private final int MAX = 10;
    private int count = 0;

    public synchronized void put(T t) {
        while (MAX == list.size()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(t);
        count++;
        this.notifyAll();
    }
    public synchronized T take() {
        while (list.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T t = list.removeFirst();
        count--;
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {
        MyContainer1<String> c1 = new MyContainer1<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println("消费===" + c1.take());
                }
            }, "c" + i).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    System.out.println("生产=" + Thread.currentThread().getName() + "-" + j);
                    c1.put(Thread.currentThread().getName() + "-" + j);
                }
            }, "p" + i).start();
        }
    }
}
