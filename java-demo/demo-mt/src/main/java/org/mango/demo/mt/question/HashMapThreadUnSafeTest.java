package org.mango.demo.mt.question;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * HashMap并发下线程安全测试
 * @Author: mango
 * @Date: 2022/7/18 4:58 下午
 */
public class HashMapThreadUnSafeTest {
    static Map<String,String> map = new HashMap<>();
    // static Map<String,String> map = new ConcurrentHashMap<>(); // 并发下可使用ConcurrentHashMap
    static class AddThread extends Thread{
        int start = 0;
        public AddThread(int start){
            this.start = start;
        }
        @Override
        public void run() {
           for(int i=start;i<100000;i+=2){
               map.put(Integer.toString(i),Integer.toBinaryString(i));
           }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new AddThread(0);
        Thread t2 = new AddThread(1);
        t1.start();t2.start();
        t1.join();t2.join();
        System.out.println(map.size());
    }
}
/**
 * 结果：（比100000小的数字）
 * 98146
 * jdk8中已经规避出现死循环的问题（put方法内e=e.next多线程下可能成环）
 * 多线程下请使用ConcurrentHashMap
 */