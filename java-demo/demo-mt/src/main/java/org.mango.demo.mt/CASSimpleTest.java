package org.mango.demo.mt;

import sun.misc.Unsafe;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * cas测试
 */
public class CASSimpleTest {
    Integer count;
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InterruptedException {
        // 获得Unsafe对象，利用反射
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        CASSimpleTest casSimpleTest = new CASSimpleTest();
        casSimpleTest.count = 0;
        List<Thread> list = new ArrayList<>();
        for(int i=0;i<100000;i++){
            Thread t = new Thread(()->{
                try {
                    while (!unsafe.compareAndSwapInt(casSimpleTest.count,
                            unsafe.objectFieldOffset(Integer.class.getDeclaredField("value")),
                            casSimpleTest.count, casSimpleTest.count + 1)) {
                        System.out.println(Thread.currentThread().getName() + "获得false一次");
                    }
                }catch (NoSuchFieldException e){
                    e.printStackTrace();
                }
            });
            t.setName("T"+(i+1));
            t.start();
            list.add(t);
        }
        for(Thread t : list){
            t.join();
        }
        System.out.println(Thread.currentThread().getName()+" -- "+ casSimpleTest.count);
    }
}
