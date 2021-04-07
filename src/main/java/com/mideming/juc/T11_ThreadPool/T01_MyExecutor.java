package com.mideming.juc.T11_ThreadPool;

import java.util.concurrent.Executor;

public class T01_MyExecutor implements Executor {

    public static void main(String[] args) {
        new T01_MyExecutor().execute(() -> {
            System.out.println("myExecutor");
        });
    }

    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
