package com.mideming.juc.sync;

import java.util.concurrent.TimeUnit;

/**
 * 准备睡1秒
 * 准备睡2秒
 * 1秒睡完了
 * 0.0
 * 2秒睡完了
 * 100.0
 *
 *
 *
 */
public class Account2 {
    String name;
    double balance;

    public synchronized void set(String name, double balance) {

        try {
            System.out.println("准备睡2秒");
            Thread.sleep(2000);
            System.out.println("2秒睡完了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.name = name;

        this.balance = balance;
    }

    public synchronized double getBalance(String name) {
        return this.balance;
    }


    public static void main(String[] args) {
        Account2 a = new Account2();
        new Thread(()->a.set("zhangsan", 100.0)).start();

        try {
            System.out.println("准备睡1秒");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("1秒睡完了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(a.getBalance("zhangsan"));

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(a.getBalance("zhangsan"));
    }

}
