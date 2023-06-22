package com.mango.demo.future.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.Executors;

/**
 * disruptor 示例（无锁）- 无锁内存队列框架
 * @Author: mango
 * @Date: 2022/7/26 12:29 下午
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        // 大小需要是2的幂
        int bufferSize = 1024;
        Disruptor<PCData> disruptor = new Disruptor<>(
                new PCDataFactory(),
                bufferSize,
                Executors.defaultThreadFactory(),
                ProducerType.MULTI,
                // 选择合适的策略，提高消费者的响应时间
                new BlockingWaitStrategy() // 阻塞等待策略
                // new SleepingWaitStrategy() // 休眠等待策略
                // new YieldingWaitStrategy() // 谦让等待策略
                // new BusySpinWaitStrategy() // 忙自旋等待策略，死循环
        );
        // 4个消费者
        disruptor.handleEventsWithWorkerPool(
                new Consumer(),
                new Consumer(),
                new Consumer(),
                new Consumer()
        );
        disruptor.start();

        // 生成数据
        RingBuffer<PCData> ringBuffer = disruptor.getRingBuffer();
        long size = 1000L;
        // 2个生产者
        new Thread(()->{
            Producer producer = new Producer(ringBuffer);
            ByteBuffer bb = ByteBuffer.allocate(8);
            for(long i = 0L;i<size;i++){
                bb.putLong(0,i);
                producer.pushData(bb);
                System.out.println(Thread.currentThread().getName() + " - 产生数据:"+i);
            }
        }).start();
        new Thread(()->{
            Producer producer = new Producer(ringBuffer);
            ByteBuffer bb = ByteBuffer.allocate(8);
            for(long i = size;i<2*size;i++){
                bb.putLong(0,i);
                producer.pushData(bb);
                System.out.println(Thread.currentThread().getName() + " - 产生数据:"+i);
            }
        }).start();


    }
}
