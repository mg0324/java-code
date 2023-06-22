package com.mango.base.interface2;
/**
 * 苹果牌手机
 */
public class Apple implements ChargeFace, Price {

    @Override
    public void type() {
        System.out.println("Apple使用的是Lightning 接口");
    }

    @Override
    public void value() {
        System.out.println("Apple Phone价值7000-12000RMB");
    }
    
}
