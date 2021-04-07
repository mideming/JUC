package com.mideming.juc.T11_ThreadPool;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class T06_CompletableFuture {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(T06_CompletableFuture::priceOfTM);
        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(T06_CompletableFuture::priceOfTB);
        CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(T06_CompletableFuture::priceOfJD);
        CompletableFuture.allOf(futureTM, futureTB, futureJD).join();
        long end = System.currentTimeMillis();
        System.out.println("use time " + (end - start));
    }

    public static double priceOfTM() {
        delay();
        return 1.00;
    }
    public static double priceOfTB() {
        delay();
        return 2.00;
    }
    public static double priceOfJD() {
        delay();
        return 3.00;
    }

    public static void delay() {
        int time = new Random().nextInt(500);
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after sleep" + time);
    }
}
