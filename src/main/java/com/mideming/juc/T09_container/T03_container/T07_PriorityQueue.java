package com.mideming.juc.T09_container.T03_container;

import java.util.PriorityQueue;

/**
 * 内部进行排序
 */
public class T07_PriorityQueue {
    public static void main(String[] args) {
        PriorityQueue<String> q = new PriorityQueue<>();
        q.add("c");
        q.add("e");
        q.add("a");
        q.add("d");
        q.add("z");
        for (int i = 0; i < 5; i++) {
            System.out.println(q.poll());
        }
    }
}
