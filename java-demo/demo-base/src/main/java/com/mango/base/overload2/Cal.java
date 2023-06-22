package com.mango.base.overload2;

public class Cal {
    // 整型 + 整型的加法方法
    public int add(int a,int b){
        return a + b;
    }
    // 3个数相加的加法方法
    public int add(int a,int b,int c){
        return a + b + c;
    }
    // 小数 + 小数的加法方法
    public float add(float a,float b){
        return a + b;
    }
}
