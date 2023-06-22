package org.mango.demo.mt;

/**
 * 线程组测试
 * @Author: mango
 * @Date: 2022/6/30 11:03 下午
 */
public class ThreadGroupTest {
    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("HttpHandler");
        Thread t1 = new Thread(group,()->{},"T1");
        Thread t2 = new Thread(group,()->{},"T2");
        Thread t3 = new Thread(group,()->{},"T3");
        Thread t4 = new Thread(group,()->{},"T4");
        group.list();
    }
}
