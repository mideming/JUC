package com.mideming.juc.T08_RefType;

import java.lang.ref.SoftReference;

/**
 * 软引用
 * 系统内存不够用的时候会回收，够的话就不会回收
 */
public class T02_SoftRefer {
    public static void main(String[] args) {
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024 * 1024 * 10]);
        System.out.println(m.get());
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(m.get());
        // 再分配一个数组，heap将装不下，这时候系统会垃圾回收，先回收一次，如果不够，会把软引用干掉
        byte[] b = new byte[1024 * 1024 * 7];
        System.out.println(m.get());
    }
}
