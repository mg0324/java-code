package org.mango.demo.mt;

import java.util.concurrent.Semaphore;

/**
 * 模拟3个窗口卖票，10个人买
 *
 * 非公平信号量：new Semaphore(3)，不遵循先进先出
 * t0买到了票
 * t2买到了票
 * t1买到了票
 * t1买完离开
 * t0买完离开
 * t3买到了票
 * t4买到了票
 * t2买完离开
 * t5买到了票
 * t5买完离开
 * t3买完离开
 * t4买完离开
 * t7买到了票
 * t6买到了票
 * t8买到了票
 * t7买完离开
 * t8买完离开
 * t9买到了票
 * t6买完离开
 * t9买完离开
 *
 * 公平信号量： new Semaphore(3,true)，遵循先进先出
 * t0买到了票
 * t1买到了票
 * t2买到了票
 * t0买完离开
 * t2买完离开
 * t1买完离开
 * t4买到了票
 * t3买到了票
 * t5买到了票
 * t4买完离开
 * t3买完离开
 * t5买完离开
 * t7买到了票
 * t6买到了票
 * t8买到了票
 * t7买完离开
 * t8买完离开
 * t9买到了票
 * t6买完离开
 * t9买完离开
 */
public class SemaphoreTest {
    public static void main(String[] args) throws InterruptedException {
        // 3个信号量，模拟3个买票窗口
        Semaphore semaphore = new Semaphore(3,true);

        for(int i=0;i<10;i++) {
            Thread t1 = new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "买到了票");
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + "买完离开");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            t1.setName("t"+i);
            t1.start();
        }
    }
}
