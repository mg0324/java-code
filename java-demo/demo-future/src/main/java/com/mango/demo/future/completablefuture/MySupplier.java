package com.mango.demo.other.completablefuture;

import java.util.function.Supplier;

/**
 * @Author: mango
 * @Date: 2022/7/28 12:04 上午
 */
public class MySupplier implements Supplier<String> {
    private String str;
    public MySupplier(String str){
        this.str = str;
    }

    @Override
    public String get() {
        // 模拟比较耗时的操作
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello jdk CompletableFuture," + str;
    }
}
