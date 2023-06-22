package org.mango.demo.mt.thread;

/**
 * 屏障测试
 * @Author: mango
 * @Date: 2022/7/9 9:47 上午
 */
public class BarrierTest {
    volatile static boolean complete;
    static int a;
    static int b;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
           // 模拟加载配置a,b
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a = 100;
            b = 200;
            // 加载完成后，设置标志为完成
            complete = true;
        });
        Thread t2 = new Thread(()->{
            // 使用配置
            while (!complete){

            }
            int c = a + b;
            System.out.println("c="+c);
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("程序执行完成");
    }
}
