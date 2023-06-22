package org.mango.demo.mt;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * cas测试 使用线程池来增大竞争，是的cas失败的次数增多
 */
public class CASUseThreadPoolTest {
    Integer count;
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InterruptedException {
        // 获得Unsafe对象，利用反射
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        ExecutorService es = Executors.newFixedThreadPool(1000);

        CASUseThreadPoolTest a = new CASUseThreadPoolTest();
        a.count = 0;
        List<Thread> list = new ArrayList<>();
        for(int i=0;i<100000;i++){
            Thread t = new Thread(()->{
                try {
                    while (!unsafe.compareAndSwapInt(a.count,
                            unsafe.objectFieldOffset(Integer.class.getDeclaredField("value")),
                            a.count, a.count+1)) {
                        System.out.println(Thread.currentThread().getName() + "获得false一次");
                    }
                }catch (NoSuchFieldException e){
                    e.printStackTrace();
                }
            });
            t.setName("T"+(i+1));
            es.submit(t);
            t.join();
        }
        System.out.println(Thread.currentThread().getName()+" -- "+ a.count);
        es.shutdownNow();
    }
}
