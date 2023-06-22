package com;
/**
 * @Author: mango
 * @Date: 2022/8/14 12:00 上午
 */
public class Test {
    static volatile boolean c = false;
    public static void main(String[] args) {
        int a = 1;
        long b = 2;
        c = true;
        System.out.printf("hello world!" + a + b + c);
        int len = 1000;
        int result = 0;
        for(int i=0;i<len;i++){
            result += add(i,len);
        }
        System.out.printf("result="+result);
    }

    public static int add(int a,int b){
        return cc(a + b);
    }

    public static int cc(int c){
        return c * 2;
    }
}
