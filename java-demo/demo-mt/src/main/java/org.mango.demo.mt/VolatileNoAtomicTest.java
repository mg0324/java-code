package org.mango.demo.mt;

/**
 * volatile不保证原子性
 * @Author: mango
 * @Date: 2022/7/4 11:32 下午
 */
public class VolatileNoAtomicTest {
    private static volatile int number = 0;

    static class AdderThread extends Thread{
        @Override
        public void run() {
            for(int i=0;i<10000;i++){
                number++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new AdderThread();
        t1.start();
        Thread t2 = new AdderThread();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(number);
    }
}
/**
 * 结果：
 * 有时候输出小于20000的值，说明number++无法保证原子性
 */
