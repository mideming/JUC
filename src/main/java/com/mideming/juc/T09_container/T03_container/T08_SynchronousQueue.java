package com.mideming.juc.T09_container.T03_container;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 容量为0，一个线程传递数据给另一个线程
 */
public class T08_SynchronousQueue {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> strs = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        // 阻塞等待消费者消费
        strs.put("aaa");
        //strs.put("bbb");
        // add会报错，不能往里装东西
        //strs.add("aaa");
        System.out.println(strs.size());
    }

}
