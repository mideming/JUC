package com.mideming.juc.T09_container.T02_FromVectorToQueue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class T04_TicketSellerQueue {
    static Queue<String> tickets = new ConcurrentLinkedQueue<>();
    static{
        for (int i = 0; i < 1000; i++) {
            tickets.add("票编号" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickets.size() > 0) {
                    System.out.println("销售了" + tickets.poll());
                }
            }).start();
        }
    }
}
