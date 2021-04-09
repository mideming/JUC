package com.mideming.juc.T11_ThreadPool;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class T13_ForkJoinPool {
    static int[] nums = new int[10000000];
    static final int MAX_NUM = 500000;
    static Random r = new Random();

    static void test() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextInt(100);
        }
        long end = System.currentTimeMillis();
        System.out.println("---消耗时间" + (end - start));
        System.out.println("---" + Arrays.stream(nums).sum()); //stream api
    }


    static class AddTask extends RecursiveAction {

        int start, end;

        AddTask(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        protected void compute() {

            if (end - start <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) sum += nums[i];
                System.out.println("from:" + start + " to:" + end + " = " + sum);
            } else {

                int middle = start + (end - start) / 2;

                AddTask subTask1 = new AddTask(start, middle);
                AddTask subTask2 = new AddTask(middle, end);
                subTask1.fork();
                subTask2.fork();
            }


        }

    }


    static class AddTaskRet extends RecursiveTask<Long> {

        private static final long serialVersionUID = 1L;
        int start, end;

        AddTaskRet(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        protected Long compute() {

            if (end - start <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) sum += nums[i];
                return sum;
            }

            int middle = start + (end - start) / 2;

            AddTaskRet subTask1 = new AddTaskRet(start, middle);
            AddTaskRet subTask2 = new AddTaskRet(middle, end);
            subTask1.fork();
            subTask2.fork();

            return subTask1.join() + subTask2.join();
        }

    }

    public static void main(String[] args) throws IOException {
		/*ForkJoinPool fjp = new ForkJoinPool();
		AddTask task = new AddTask(0, nums.length);
		fjp.execute(task);*/
        test();
        long start = System.currentTimeMillis();
        ForkJoinPool fjp = new ForkJoinPool();
        AddTaskRet task = new AddTaskRet(0, nums.length);
        fjp.execute(task);
        long result = task.join();
        long end = System.currentTimeMillis();
        System.out.println("---消耗时间" + (end - start));
        System.out.println(result);

        //System.in.read();

    }

}
