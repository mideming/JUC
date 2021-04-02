package com.mideming.juc.T09_container.T03_container;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 队列相比list，多了offer，poll，peek方法
 */
public class T04_ConcurrentQueue {
    public static void main(String[] args) {
        Queue<String> strs = new ConcurrentLinkedQueue<>();

        for(int i=0; i<10; i++) {
            strs.offer("a" + i);  //add
        }

        System.out.println(strs);

        System.out.println(strs.size());

        // 取出来，并且remove
        System.out.println(strs.poll());
        System.out.println(strs.size());

        // 取出来，并不会remove
        System.out.println(strs.peek());
        System.out.println(strs.size());

    }

}
