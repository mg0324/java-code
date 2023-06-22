package org.mango.demo.mt;


/**
 * volatile 可见性测试
 */
public class VolatileVisibilityTest {
    private static volatile boolean ready;
    private static int number;

    private static class ReaderThread extends Thread{
        @Override
        public void run() {
            while (!ready);
            System.out.println(number);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        Thread.sleep(1000);
        number = 42;
        ready = true;
        Thread.sleep(10000);
    }
}
/**
 * 因为JMM保证了volatile变量ready的可见性，在main线程中修改为true；
 * ReaderThread线程能应用到这个修改，则while(!ready)循环得以跳过。
 * 则输出42，,10秒后退出程序
 */
