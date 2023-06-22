package org.mango.demo.mt.thread;

import lombok.SneakyThrows;

import java.util.concurrent.*;

/**
 * 线程池测试
 * @Author: mango
 * @Date: 2022/6/24 8:35 上午
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                3, // 核心线程数
                5, // 最大线程数
                30l, // 超过核心线程数时，线程存活时间
                TimeUnit.SECONDS, // 单位
                new ArrayBlockingQueue<>(10), // 工作队列，可以设置为直接提交队列，无界队列，有界队列
                Executors.defaultThreadFactory(), // 线程创建工厂
                new ThreadPoolExecutor.CallerRunsPolicy() // 拒绝策略
        ){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行："+ t.getName());
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行完成："+ r);
            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
        };
        int size = 20;
        CountDownLatch cdl = new CountDownLatch(size);
        for(int i=0;i<size;i++){
            threadPoolExecutor.submit(new MyThread(threadPoolExecutor,cdl));
        }
        try {
            cdl.await();
            //threadPoolExecutor.shutdown();
            System.out.println("所有任务执行完成");
            threadPoolExecutor.shutdown();
//            while (true){
//                Thread.sleep(3000);
//                System.out.println(threadPoolExecutor);
//            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class MyThread implements Runnable {
    private ThreadPoolExecutor tpe;
    private CountDownLatch cdl;

    public MyThread(ThreadPoolExecutor tpe,CountDownLatch cdl){
        this.tpe = tpe;
        this.cdl = cdl;
    }

    @SneakyThrows
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" start");
        int random = (int) (Math.random()*10 + 3);// 3-13
        Thread.sleep(random*1000);
        System.out.println(tpe);
        System.out.println(Thread.currentThread().getName()+" end");
        cdl.countDown();
    }
}

class MyCallable implements Callable<Boolean>{

    @Override
    public Boolean call() throws Exception {
        return null;
    }
}