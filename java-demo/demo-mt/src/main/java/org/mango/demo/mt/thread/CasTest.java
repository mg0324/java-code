package org.mango.demo.mt.thread;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * CAS测试
 * @Author: mango
 * @Date: 2022/6/29 10:43 上午
 */
public class CasTest {
    public static void main(String[] args) {
        AtomicLong al = new AtomicLong(100);
        long result = al.incrementAndGet();
//        while (!al.compareAndSet(100,150)){
//            System.out.println("设置成功");
//        }
        System.out.println(result);
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Lock readLock = new ReentrantReadWriteLock().readLock();
        Lock writeLock = new ReentrantReadWriteLock().writeLock();

        System.out.println(Runtime.getRuntime().availableProcessors());

        try {
            al.wait();
            Thread t1 = new Thread();
            t1.start();
            Thread.yield();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
