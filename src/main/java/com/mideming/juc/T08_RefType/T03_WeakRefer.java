package com.mideming.juc.T08_RefType;

import java.lang.ref.WeakReference;

/**
 * 弱引用：遭到gc就会回收
 * 一般用在容器里，WeakHashMap
 * 为什么ThreadLocalMap的key（ThreadLocal）是弱引用？
 * 若是强引用，即使ThreadLocal为空，但是key的引用依然指向ThreadLocal对象，所以会有内存泄漏，而使用弱引用则不会
 * 但还是有内存泄漏存在，ThreadLocal被回收，key的值变为null，则导致整个value再也无法被访问到，因此依然存在内存泄漏，需要手动remove
 */
public class T03_WeakRefer {
    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());
        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());

        ThreadLocal<M> tl = new ThreadLocal<>();
        tl.set(new M());
        tl.remove();
    }
}
