package com.mideming.juc.T09_container.T03_container;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class T06_ArrayBlockingQueue {
    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);

    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            strs.put("a" + i);
        }
        // 满了就会等待，程序阻塞
        strs.put("aaa");
        // 会抛异常
//        strs.add("aaa");
        // 不会抛异常，有返回值
//        strs.offer("aaa");
        // 尝试添加1秒钟
        strs.offer("aaa", 1, TimeUnit.SECONDS);

        System.out.println(strs);
    }

}
