package com.mideming.juc.T09_container.T03_container;

import java.util.concurrent.LinkedTransferQueue;

/**
 * put只有在满了才会阻塞
 * transfer，阻塞，等取走再走
 * SynchronousQueue只能传递一个数据，TransferQueue可以传多个
 * 应用场景：要等结果完成才能继续下一步操作
 */
public class T09_TransferQueue {
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();
        new Thread(() -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.transfer("aaa");

        //strs.put("aaa");


		/*new Thread(() -> {
			try {
				System.out.println(strs.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();*/


    }

}
