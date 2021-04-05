package com.mideming.juc.T10_interviewA1B2C3;

import java.util.concurrent.locks.LockSupport;

public class T02_locksupport {
    static Thread t1 = null;
    static Thread t2 = null;
    public static void main(String[] args) {
        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();
        t1 = new Thread( () -> {
            for (int i = 0; i < aI.length; i++) {
                System.out.println(aI[i]);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });
        t2 = new Thread(() -> {
            for (int i = 0; i < aC.length; i++) {
                LockSupport.park();
                System.out.println(aC[i]);
                LockSupport.unpark(t1);
            }
        });
        t1.start();
        t2.start();
    }
}
