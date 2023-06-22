package org.mango.demo.mt.sync;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @Author: mango
 * @Date: 2022/7/26 12:05 下午
 */
public class Test2 {
    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(6);
        // 使用自定义对象做锁
        final B lock = new B();
        System.out.println(ClassLayout.parseInstance(lock).toPrintable(lock));
        // 第一个线程获取锁
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println(ClassLayout.parseInstance(lock).toPrintable());
            }
            synchronized (lock) {
                System.out.println(ClassLayout.parseInstance(lock).toPrintable());
            }
        });
        t1.start();
        t1.join();

        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
        // 第二个线程获取锁
        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println(ClassLayout.parseInstance(lock).toPrintable());
            }
            // sleep一下，让其它线程来竞争
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lock) {
                System.out.println(ClassLayout.parseInstance(lock).toPrintable());
            }
        });
        t2.start();

        // 第三个线程获取锁
        Thread t3 = new Thread(() -> {
            synchronized (lock) {
                System.out.println(ClassLayout.parseInstance(lock).toPrintable());
            }
        });
        t3.start();

        t3.join();
        t2.join();

        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
    }
    public static class B {}
}
