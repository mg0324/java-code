package org.mango.demo.mt.thread;

/**
 * volatile 汇编测试
 * @Author: mango
 * @Date: 2022/7/1 5:17 下午
 */
public class VolatileAssemblyTest {
    private static int number;
    public static void main(String[] args) {
        number = 100;
        int a = 1;
        a++;
        System.out.println("a="+a);
    }
}
