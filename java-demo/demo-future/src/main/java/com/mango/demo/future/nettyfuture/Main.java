package com.mango.demo.other.nettyfuture;

import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;

/**
 * @Author: mango
 * @Date: 2022/7/28 12:01 上午
 */
public class Main {
    public static void main(String[] args) {
        // 创建netty线程组
        EventExecutorGroup group = new DefaultEventExecutorGroup(4);
        // 提交任务
        Future<String> future = group.submit(new MyCallable("测试"));
        System.out.println("处理其他逻辑1");
        System.out.println("处理其他逻辑2");
        future.addListener(new FutureListener<String>(){
            @Override
            public void operationComplete(Future<String> future) throws Exception {
                String result = future.get();
                System.out.println("(netty)异步处理结果为:"+result);
            }
        });
        group.shutdownGracefully();
    }
}
