package com.mideming.juc.T09_container.T02_FromVectorToQueue;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class T02_TicketSellerVector {
    static Vector<String> tickets = new Vector<>();
    static{
        for (int i = 0; i < 1000; i++) {
            tickets.add("票编号" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickets.size() > 0) {
                    // 2个原子操作之间没有原子
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("销售了" + tickets.remove(0));
                }
            }).start();
        }
    }
}
