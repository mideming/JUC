package com.mideming.juc.T10_interviewA1B2C3;

public class T01_sync_wait_notify {
    public static void main(String[] args) {
        Object o = new Object();
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();
        new Thread(() -> {
            synchronized (o) {
                for (int i = 0; i < aI.length; i++) {
                    System.out.println(aI[i]);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }).start();
        new Thread(() -> {
            synchronized (o) {
                for (int i = 0; i < aC.length; i++) {
                    System.out.println(aC[i]);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }).start();
    }

}
