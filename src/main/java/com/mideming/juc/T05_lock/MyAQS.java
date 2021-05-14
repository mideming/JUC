package com.mideming.juc.T05_lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class MyAQS {

    private final Sync sync;

    public MyAQS() {
        sync = new Sync();
    }

    public void lock() {
        sync.acquire(1);
    }

    public void unlock() {
        sync.release(1);
    }

    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            return compareAndSetState(0, 1);
        }

        @Override
        protected boolean tryRelease(int arg) {
            setState(0);
            return true;
        }

        // 该线程是否正在独占资源，只有用到 Condition 才需要去实现
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final MyAQS lock = new MyAQS();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(200);
            new Thread(new TestLock(lock), String.valueOf(i)).start();
        }
        Thread.sleep(100000);
    }

    static class TestLock implements Runnable {
        private MyAQS lock;

        public TestLock(MyAQS lock) throws InterruptedException {
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
//                lock.lock();
                Thread.sleep(1000);
                System.out.println(String.format("Thread %s Completed", Thread.currentThread().getName()));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
