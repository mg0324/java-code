package org.mango.demo.mt.thread;

/**
 * volatile测试
 * @Author: mango
 * @Date: 2022/6/29 9:08 上午
 */
public class VolatileTest {
    private static boolean run = true;
    private static volatile int count = 1;

    public static void main(String[] args) throws InterruptedException {
        Thread t2 = new Thread(()->{
            run = false;
            System.out.println("时间到，修改run为false");
            System.out.println("count="+count);
            count = 100;
        });
        Thread t1 = new Thread(()->{
            while (run) {
                System.out.println(run);
                count++;
            }
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1 count="+count);
        });
        t1.start();
        Thread.sleep(10);
        t2.start();
        t2.join();
        System.out.println("final count="+count);
    }
}
