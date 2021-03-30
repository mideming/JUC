package com.mideming.juc.lock;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 阶段性的处理
 * 设置通过的线程数，满了之后就进入下一个阶段
 */
public class T08_Phaser {
    static Random r = new Random();
    static MarriagePhaser phaser = new MarriagePhaser();

    public static void main(String[] args) {
        // 定义一共7个线程
        phaser.bulkRegister(7);
        for (int i = 0; i < 5; i++) {
            new Thread(new Person("P" + i)).start();
        }
        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();
    }

    static class Person implements Runnable{

        String name;

        public Person (String name) {
            this.name = name;
        }

        public void arrive () {
            milliSleep(r.nextInt(1000));
            System.out.println(name + "到达现场");
            // 阻塞，等第一阶段满足条件，调用onAdvance的case 0，然后进入下一个阶段
            phaser.arriveAndAwaitAdvance();
        }

        public void eat () {
            milliSleep(r.nextInt(1000));
            System.out.println(name + "吃完了");
            phaser.arriveAndAwaitAdvance();
        }

        public void leave () {
            milliSleep(r.nextInt(1000));
            System.out.println(name + "离开了");
            phaser.arriveAndAwaitAdvance();
        }

        public void hug () {
            if (name.equals("新郎") || name.equals("新娘")) {
                milliSleep(r.nextInt(1000));
                System.out.println(name + "洞房");
                phaser.arriveAndAwaitAdvance();
            } else {
                phaser.arriveAndDeregister();
            }
        }

        @Override
        public void run() {
            arrive();
            eat();
            leave();
            hug();
        }
    }

    static class MarriagePhaser extends Phaser {
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0:
                    System.out.println("所有人到齐了" + registeredParties);
                    System.out.println();
                    return false;
                case 1:
                    System.out.println("所有人吃完了" + registeredParties);
                    System.out.println();
                    return false;
                case 2:
                    System.out.println("所有人离开了" + registeredParties);
                    System.out.println();
                    return false;
                case 3:
                    System.out.println("婚礼结束！新郎新娘入洞房" + registeredParties);
                    return true;
                default:
                    return true;
            }
        }
    }


    static void milliSleep(int milli) {
        try {
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
