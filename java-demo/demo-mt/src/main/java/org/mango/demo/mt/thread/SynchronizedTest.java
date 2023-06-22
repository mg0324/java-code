package org.mango.demo.mt.thread;

/**
 * synchronized测试
 * @Author: mango
 * @Date: 2022/6/29 9:08 上午
 */
public class SynchronizedTest {
    private static boolean run = true;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            while (run) {
                System.out.println(run);
            }
        });
        t1.start();
        Thread.sleep(100);
        Thread t2 = new Thread(()->{
            run = false;
            System.out.println("时间到，修改run为false");
        });
        t2.start();
    }
}
