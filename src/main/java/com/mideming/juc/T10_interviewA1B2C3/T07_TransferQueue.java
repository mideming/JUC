package com.mideming.juc.T10_interviewA1B2C3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;


public class T07_TransferQueue {
    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();
        TransferQueue<Character> q1 = new LinkedTransferQueue<>();
        Thread t1 = new Thread( () -> {
            for (int i = 0; i < aI.length; i++) {
                try {
                    System.out.println(q1.take());
                    q1.transfer(aI[i]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < aC.length; i++) {
                try {
                    q1.transfer(aC[i]);
                    System.out.println(q1.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
    }
}
