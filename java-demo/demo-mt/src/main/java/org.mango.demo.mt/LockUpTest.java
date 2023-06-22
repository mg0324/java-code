package org.mango.demo.mt;

import org.openjdk.jol.info.ClassLayout;

/**
 * 锁升级测试
 * -XX:+UseBiasedLocking 默认1.6之后就开启了偏向锁
 * -XX:BiasedLockingStartupDelay=5  偏向锁启动延迟，单位秒，系统默认值是4
 *
 * 结论：
 *  当不开启偏向锁时，能得到 001（无锁） -> 000（轻量级锁） -> 010（重量级锁）
 *  开启偏向锁时，并设置延时5秒，new之后sleep 6秒，for循环内1个线程，则能得到 001(无锁） -> 000（轻量级锁） -> 101（偏向锁）
 *  开启偏向锁时，并设置延时5秒，new之后sleep 6秒，for循环内2个线程及以上，则能得到 001（无锁） -> 000（轻量级锁） -> 010（重量级锁）
 */
public class LockUpTest {
    // 锁对象
    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        // new状态 -- 001
        System.out.println(Thread.currentThread().getName() + " -- " + ClassLayout.parseInstance(lock).toPrintable());
        Thread.sleep(6000);

        System.out.println(Thread.currentThread().getName() + " -- " + ClassLayout.parseInstance(lock).toPrintable());

        synchronized (lock){
            // 轻量级锁 -- 000
            System.out.println(Thread.currentThread().getName() + " -- " + ClassLayout.parseInstance(lock).toPrintable());
        }

        // 偏向锁 -- 101
        new Thread(()->{
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " -- " + ClassLayout.parseInstance(lock).toPrintable());
            }
        }).start();

        // 重量级锁 -- 010（当线程数大于1）
        for(int i=0;i<2;i++){
            new Thread(()->{
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + " -- " + ClassLayout.parseInstance(lock).toPrintable());
                }
            }).start();
        }
    }
}
