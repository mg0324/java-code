package org.mango.demo.mt;

/**
 * 守护线程测试
 * @Author: mango
 * @Date: 2022/6/30 11:26 下午
 */
public class DaemonTest {
    // 低优先级
    public final static int MIN_PRIORITY = 1;
    // 中优先级
    public final static int NORM_PRIORITY = 5;
    // 高优先级
    public final static int MAX_PRIORITY = 10;
    public static class DaemonT extends Thread{
        @Override
        public void run() {
            while (true){
                System.out.println("i am alive!");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread t = new DaemonT();
        t.setDaemon(true);// 设置为守护线程
        t.start();
        t.setPriority(Thread.MAX_PRIORITY);
        Thread.sleep(2000);
    }
}
