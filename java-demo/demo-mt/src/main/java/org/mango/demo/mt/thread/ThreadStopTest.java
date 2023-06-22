package org.mango.demo.mt.thread;

/**
 * 线程停止测试
 * @Author: mango
 * @Date: 2022/6/24 9:40 下午
 */
public class ThreadStopTest {

    static class MyThread extends Thread {
        volatile boolean stop = false;
        public void run() {
            while (!stop) {
                System.out.println(getName() + " is running");
                try {
                    sleep(10000);
                    System.out.println(getName() + " is xxxx");
                } catch (InterruptedException e) {
                    System.out.println("week up from blcok...");
                    //stop = true; // 在异常处理代码中修改共享变量的状态 }
                }
                System.out.println(getName() + " is exiting...");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread m1 = new MyThread();
        System.out.println("Starting thread...");
        Thread.sleep(10000);
        m1.start();
        System.out.println("Interrupt thread...: " + m1.getName());
        Thread.sleep(10000);
        //m1.stop = true; // 设置共享变量为true
        m1.interrupt(); // 阻塞时退出阻塞状态
        Thread.sleep(3000); // 主线程休眠3秒以便观察线程m1的中断情况
        System.out.println("Stopping application...");
    }
}

