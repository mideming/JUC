package com.mideming.juc.sync;

import java.util.concurrent.TimeUnit;

public class Account {
    String name;
    Double balance;

    public synchronized void setBalance(String name, Double balance) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.name = name;
        this.balance = balance;
    }

    public Double getBalance(String name) {
        return this.balance;
    }

    public static void main(String[] args) {
        Account account = new Account();
        new Thread(()->{
            account.setBalance("zhangsan",100.0);
        }).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(account.getBalance("zhangsan"));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(account.getBalance("zhangsan"));
    }
}
