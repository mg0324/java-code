package com.mango.demo.jvm.gc;

/**
 * 垃圾收集测试
 * @Author: mango
 * @Date: 2022/8/16 10:03 上午
 */
public class GCTest {
    private static final int _1MB = 1024 * 1024;

    /**
     * 大对象直接进入老年代，对Serial和ParNew垃圾收集有效
     * vm参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:+UseSerialGC -XX:PretenureSizeThreshold=3145728
     */
    public static void bigObj2old(){
        byte[] a;
        a = new byte[4 * _1MB];
    }
    /**
     * 长期存活对象进入老年代
     * vm参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:+UseSerialGC
     * -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
     */
    public static void liveObj2old(){
        byte[] a,b,c;
        a = new byte[_1MB / 4];
        b = new byte[4 * _1MB];
        c = new byte[4 * _1MB];
    }
    /**
     * Survivor空间内相同年龄所有对象大小总和大于Survivor空间的一半，年龄大于等于的对象可以直接进入老年代，无须等到默认15岁。
     * vm参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:+UseSerialGC
     * -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution
     */
    public static void dynamicAgeObj2old(){
        byte[] a,b,c,d,e;
        a = new byte[_1MB / 4];
        b = new byte[_1MB / 4];
        // a + b 大于survivor空间一半
        c = new byte[4 * _1MB];
        d = new byte[4 * _1MB];
        d = null;// 让被回收
        e = new byte[4 * _1MB];
    }

    /**
     * vm参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:+UseSerialGC -XX:MaxTenuringThreshold=15
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("hello j9");
        A a = new A("小刚");
        while (true){
            Thread.sleep(100000);
        }
    }

    static class A {
        private String name;

        public A(String name) {
            this.name = name;
        }
    }
}
