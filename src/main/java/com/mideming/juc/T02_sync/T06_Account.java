package com.mideming.juc.T02_sync;

import java.util.concurrent.TimeUnit;

/**
 * 面试题：模拟银行账户
 * 对业务写方法加锁
 * 对业务读方法不加锁
 * 这样行不行？
 *
 * 容易产生脏读问题（dirtyRead）
 */
public class T06_Account {
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

    public /*synchronized*/ double getBalance(String name) {
        return this.balance;
    }


    public static void main(String[] args) {
        T06_Account a = new T06_Account();
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
