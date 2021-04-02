package com.mideming.juc.T06_interview.interview01;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
/**
 * 实现一个容器，提供2个方法，add,size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 * 给list添加volatile之后，t2能够接到通知，但是t2线程的死循环浪费cpu，如果不用死循环怎么做？
 */
public class T01_WithoutVolatile {
    List lists = new LinkedList();
    public void add(Object o) {
        lists.add(o);
    }
    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        T01_WithoutVolatile c = new T01_WithoutVolatile();
        new Thread(() -> {
            for (int i = 0; i <10; i++) {
                c.add(new Object());
                System.out.println("add" + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                if (c.size() == 5) {
                    System.out.println("跳出");
                    break;
                }
            }
        }).start();
    }
}
