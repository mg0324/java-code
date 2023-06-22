package com.mango.demo.other.jdkfuture;

import java.util.concurrent.Callable;

/**
 * @Author: mango
 * @Date: 2022/7/28 12:04 上午
 */
public class MyCallable implements Callable<String> {
    private String str;
    public MyCallable(String str){
        this.str = str;
    }
    @Override
    public String call() throws Exception {
        // 模拟比较耗时的操作
        Thread.sleep(2000);
        return "hello jdk future," + str;
    }
}
