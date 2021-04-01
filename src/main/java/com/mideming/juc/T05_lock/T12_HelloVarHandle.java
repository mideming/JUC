//package com.mideming.juc.T05_lock;
//
//import java.lang.invoke.MethodHandles;
//import java.lang.invoke.VarHandle;
//
///**
// * VarHandle也指向同一个引用，可以进行一些原子操作
// * 1.普通属性原子操作
// * 2.比反射快，直接操纵二进制码
// */
//public class T12_HelloVarHandle {
//    int x = 0;
//    private static VarHandle varHandle;
//    static {
//        try {
//            varHandle = MethodHandles.lookup().findVarHandle(T12_HelloVarHandle.class, "x", int.class);
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        T12_HelloVarHandle t12 = new T12_HelloVarHandle();
//        System.out.println(varHandle.get(t12));
//        varHandle.set(t12,1);
//        System.out.println(t12.x);
//        System.out.println(varHandle.get(t12));
//
//        varHandle.compareAndSet(t12,1,2);
//        System.out.println(t12.x);
//        System.out.println(varHandle.get(t12));
//
//        varHandle.getAndAdd(t12,3);
//        System.out.println(t12.x);
//        System.out.println(varHandle.get(t12));
//    }
//}
