package org.mango.demo.mt.sync;


import org.openjdk.jol.info.ClassLayout;

/**
 * @Author: mango
 * @Date: 2022/7/23 5:47 下午
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        //Thread.sleep(6000);
        final A o = new A();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        new Thread(()->{
            synchronized (o){
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        }).start();
    }
    static class A {}
}
