package org.mango.demo.mt.thread;


/**
 * happen-before原则测试
 * @Author: mango
 * @Date: 2022/6/30 7:06 上午
 */
public class HappenBeforeTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread();
        thread.interrupt();

        for(;;) {
            A a = new A();
            Thread.sleep(1000);
            new Thread(() -> {
                System.out.println("t1开始时间:"+System.nanoTime());
                a.setValue(100);
            }).start();
            new Thread(() -> {
                System.out.println("t2开始时间:"+System.nanoTime());
                int v = a.getValue();
                if(v == 0) {
                    System.out.println(v);
                }
            }).start();
            System.out.println("=============");
        }
    }
}

class A {
    private volatile int value = 0;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
