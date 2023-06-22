package com.mango.demo.future.singleton;

/**
 * 静态内部类 - 利用类加载一次特性确保只有一个实例（线程安全）
 * 具备优点：
 * 1. 无锁
 * 2. 懒创建
 * @Author: mango
 * @Date: 2022/7/26 10:53 下午
 */
public class StaticInnerClassSingleton {
    // 私有化构造方法
    private StaticInnerClassSingleton(){}
    // 内部类
    static class Holder{
        static StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }
    // 获取实例对象
    public static StaticInnerClassSingleton getInstance(){
        return Holder.INSTANCE;
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(()->{
                System.out.println(StaticInnerClassSingleton.getInstance());
            }).start();
        }
    }
}
