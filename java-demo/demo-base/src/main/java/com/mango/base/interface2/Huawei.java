package com.mango.base.interface2;
/**
 * 华为手机
 */
public class Huawei implements ChargeFace, Price{

    @Override
    public void type() {
        System.out.println("Huawei部分使用的是usb-c接口");
    }

    @Override
    public void value() {
        System.out.println("Huawei Phone价值在3000-10000");
    }
    
}
