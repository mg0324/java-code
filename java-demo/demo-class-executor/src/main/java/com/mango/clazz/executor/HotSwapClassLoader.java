package com.mango.clazz.executor;

/**
 * HotSwap类加载器
 * @Author: mango
 * @Date: 2022/9/2 6:58 下午
 */
public class HotSwapClassLoader extends ClassLoader {
    public HotSwapClassLoader(){
        // 设置父类加载器为加载了HotSwapClassLoader的类加载器，应该是ApplicationClassLoader
        super(HotSwapClassLoader.class.getClassLoader());
    }
    // 从字节数组数据加载为class
    public Class loadByte(byte[] data){
        return defineClass(null,data,0,data.length);
    }
}
