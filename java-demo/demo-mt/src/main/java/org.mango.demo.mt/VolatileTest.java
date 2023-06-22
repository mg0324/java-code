package org.mango.demo.mt;


/**
 * volatile作用：
 * - 禁止指令重排序（内存屏障）
 * - 保证线程可见性（CPU缓存一致性协议）
 *
   不加 volatile 可能输出：
         T2 -- count=0
         T1 -- count=0
    加上 volatile 输出：
         T1 -- count=0
         T2 -- count=100
 */
public class VolatileTest {
    private volatile int count = 0;

    public static void main(String[] args) throws InterruptedException {
        VolatileTest vt = new VolatileTest();
        Thread t1 = new Thread((()->{
            System.out.println(Thread.currentThread().getName()+" -- count="+vt.count);
            vt.count += 100;
        }));
        t1.setName("T1");
        Thread t2 = new Thread((()->{
            System.out.println(Thread.currentThread().getName()+" -- count="+vt.count);
            vt.count += 200;
        }));
        t2.setName("T2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();

    }
}
