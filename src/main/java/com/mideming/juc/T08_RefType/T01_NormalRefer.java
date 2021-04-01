package com.mideming.juc.T08_RefType;

import java.io.IOException;

/**
 * 强引用
 */
public class T01_NormalRefer {
    public static void main(String[] args) throws IOException {
        M m = new M();
        m = null;
        System.gc();
        System.in.read();
    }
}
