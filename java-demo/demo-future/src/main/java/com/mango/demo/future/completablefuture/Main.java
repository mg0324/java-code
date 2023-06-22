package com.mango.demo.other.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

/**
 * @Author: mango
 * @Date: 2022/7/28 12:01 上午
 */
public class Main {
    public static void main(String[] args){
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new MySupplier("测试"),
                Executors.newFixedThreadPool(1));
        future.whenCompleteAsync((r,t)->{
            System.out.println("(CompletableFuture)异步处理结果为:"+r);
        });
        System.out.println("处理其他逻辑1");
        System.out.println("处理其他逻辑2");
    }
}
