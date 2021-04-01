package com.mideming.juc.T08_RefType;

public class M {
    @Override
    protected void finalize() throws Throwable {
        // 对象被回收的时候会调用这个方法
        System.out.println("finalize");
    }
}
