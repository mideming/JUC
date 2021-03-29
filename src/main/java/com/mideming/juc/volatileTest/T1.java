package com.mideming.juc.volatileTest;

public class T1 {

    static volatile boolean running = true;

    public void test () {
        System.out.println("test start");
        while (running) {

        }
        System.out.println("test end");
    }

    public static void main(String[] args) {
        T1 t = new T1();
        new Thread(t::test).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        running = false;
    }
}
