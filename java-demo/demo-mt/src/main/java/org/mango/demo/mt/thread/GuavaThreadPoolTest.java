package org.mango.demo.mt.thread;

import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.Executor;

/**
 * guava 线程池测试
 * @Author: mango
 * @Date: 2022/6/28 10:39 上午
 */
public class GuavaThreadPoolTest {
    public static void main(String[] args) {
        Executor executor = MoreExecutors.newSequentialExecutor(MoreExecutors.directExecutor());
        executor.execute(()->{
            System.out.println("running in " + Thread.currentThread().getName() + " thread");
        });

    }
}
