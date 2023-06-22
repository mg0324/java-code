package com.mango.demo.other.jdkfuture;

import java.util.concurrent.*;

/**
 * @Author: mango
 * @Date: 2022/7/28 12:01 上午
 */
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        FutureTask<String> futureTask = new FutureTask<>(new MyCallable("测试"));


        // 提交任务
        executor.submit(futureTask);
        System.out.println("处理其他逻辑1");
        System.out.println("处理其他逻辑2");
        String result = futureTask.get();
        System.out.println("(jdk)异步处理结果为:"+result);
        executor.shutdown();
    }
}
