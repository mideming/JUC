package com.mideming.juc.T09_container.T02_FromVectorToQueue;

import java.util.ArrayList;
import java.util.List;

public class T03_TicketSellerListSync {
    static List<String> tickets = new ArrayList<>();
    static{
        for (int i = 0; i < 10000; i++) {
            tickets.add("票编号" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                synchronized (tickets) {
                    while (tickets.size() > 0) {
                        System.out.println("销售了" + tickets.remove(0));
                    }
                }
            }).start();
        }
    }
}
