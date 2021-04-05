package com.mideming.juc.T10_interviewA1B2C3;

public class T05_Cas {
    public volatile static int temp = 0;
    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();
        Thread t1 = new Thread( () -> {
            for (int i = 0; i < aI.length; i++) {
                while (temp != 0) {}
                System.out.println(aI[i]);
                temp = 1;
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < aC.length; i++) {
                while (temp != 1) {}
                System.out.println(aC[i]);
                temp = 0;
            }
        });
        t1.start();
        t2.start();
    }
}
