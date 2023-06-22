package com.mango.demo.other.guavafuture;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * @Author: mango
 * @Date: 2022/7/28 12:01 上午
 */
public class Main {
    public static void main(String[] args){
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(4));
        // 提交任务
        ListenableFuture<String> future = executorService.submit(new MyCallable("测试"));
        // 添加回调函数
        future.addListener(()->{
            String result = null;
            try {
                result = future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println("(guava)异步处理结果为:"+result);
        },MoreExecutors.directExecutor());
        System.out.println("处理其他逻辑1");
        System.out.println("处理其他逻辑2");
        executorService.shutdown();
    }
}
