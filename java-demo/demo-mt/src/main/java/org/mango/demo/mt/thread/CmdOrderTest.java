package org.mango.demo.mt.thread;

/**
 * 指令排序测试
 * @Author: mango
 * @Date: 2022/6/29 10:27 下午
 */
public class CmdOrderTest {
    public static void main(String[] args) {
        int a = 1, b = 2, f = 9, e = 3;
        System.out.println("执行add开始");
        int c = a + b;
        System.out.println("执行add结束，c="+c);
        System.out.println("执行sub开始");
        int d = f - e;
        System.out.println("执行sub结束，d="+d);
    }
}
