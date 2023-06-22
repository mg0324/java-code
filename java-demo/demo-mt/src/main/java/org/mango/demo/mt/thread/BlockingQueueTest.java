package org.mango.demo.mt.thread;

import lombok.SneakyThrows;

import java.util.concurrent.SynchronousQueue;

/**
 * @Author: mango
 * @Date: 2022/6/27 11:13 上午
 */
public class BlockingQueueTest {

    @SneakyThrows
    public static void main(String[] args) {
        SynchronousQueue sq = new SynchronousQueue();
        sq.put("111");
        sq.take();
        Thread t1 = new Thread(()->{
            try {
                sq.put("str1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(()->{
            try {
                System.out.println(sq.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
    }
}
