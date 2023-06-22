package org.mango.demo.mt;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 满2个，group1执行完了
 * t2跑完了
 * t0跑完了
 * 满3个，group2执行完了
 * t5跑完了
 * t3跑完了
 * t1跑完了
 * 满2个，group1执行完了
 * t6跑完了
 * t4跑完了
 * 满2个，group1执行完了
 * t10跑完了
 * t8跑完了
 * 满3个，group2执行完了
 * t11跑完了
 * t7跑完了
 * t9跑完了
 *
 * 篱笆栏，满人发车，重复利用
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2, () -> {
            System.out.println("满2个，group1执行完了");
        });
        CyclicBarrier cyclicBarrier2 = new CyclicBarrier(3, () -> {
            System.out.println("满3个，group2执行完了");
        });
        for(int i=0;i<100;i++) {
            boolean b = i % 2 == 0;
            Thread t1 = new Thread(() -> {
                try {
                    if(b){
                        // 偶数，则到篱笆1
                        cyclicBarrier1.await();
                    }else{
                        // 偶数，则到篱笆2
                        cyclicBarrier2.await();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "跑完了");

            });
            t1.setName("t"+i);
            t1.start();
        }
    }
}
