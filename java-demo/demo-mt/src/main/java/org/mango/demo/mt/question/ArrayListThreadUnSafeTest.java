package org.mango.demo.mt.question;

import java.util.ArrayList;
import java.util.List;

/**
 * ArrayList并发测试
 * @Author: mango
 * @Date: 2022/7/18 4:50 下午
 */
public class ArrayListThreadUnSafeTest {
    static List<Integer> list = new ArrayList<>(10);
    // static Vector<Integer> list = new Vector<>(10); // 并发下可使用Vector
    static class AddThread extends Thread{
        @Override
        public void run() {
            for(int i=0;i<1000;i++){
                list.add(i);
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new AddThread();
        Thread t2 = new AddThread();
        t1.start();
        t2.start();
        t1.join();t2.join();
        System.out.println(list.size());
    }
}
/**
 * 偶尔出现：（扩容时，出现越界）
 * Exception in thread "Thread-1" java.lang.ArrayIndexOutOfBoundsException: 549
 * 	at java.util.ArrayList.add(ArrayList.java:463)
 * 	at org.mango.demo.mt.int2.ArrayListThreadUnSafeTest$AddThread.run(ArrayListThreadUnSafeTest.java:19)
 * 1071
 */
