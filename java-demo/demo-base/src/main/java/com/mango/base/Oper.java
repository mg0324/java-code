package com.mango.base;

public class Oper {
    public static void main(String[] args) {
        int a = 1;// 0000 0001
        int b = 2;// 0000 0010

        System.out.println(a & b);// 0
        System.out.println(a | b);// 3
        System.out.println(a ^ b);// 3
        System.out.println(~a);// -2

        int d = 8;// 0000 1000
        System.out.println(d >> 2);// 0000 0010 = 2
        System.out.println(d << 1);// 0001 0000 = 16
        System.out.println(d >>> 1);// 0000 0100 = 4

        int a1 = 8;
        System.out.println(a1 > 9 ? a1 : a1-1); // 7
    }
}
