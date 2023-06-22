package com.mango.base.interface2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // ChargeFace接口 来引用实现类对象变量
        ChargeFace apple = new Apple();
        apple.type();
        
        // 子类引用，则可以调用到Price上的接口方法
        Huawei huawei = new Huawei();
        huawei.type();
        huawei.value();
        foo2();
        
    }

    public static <T extends Exception> void foo2() throws T {
        System.out.println(111);
        //int a = 1/0;
        throw new ClassCastException("xx");
    }
    
}

