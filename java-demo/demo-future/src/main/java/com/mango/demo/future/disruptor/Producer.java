package com.mango.demo.other.disruptor;

import com.lmax.disruptor.RingBuffer;
import java.nio.ByteBuffer;

/**
 * 生产者
 * @Author: mango
 * @Date: 2022/7/26 10:54 上午
 */
public class Producer {
    private final RingBuffer<PCData> ringBuffer;

    public Producer(RingBuffer<PCData> ringBuffer){
        this.ringBuffer = ringBuffer;
    }
    public void pushData(ByteBuffer bb){
        // 获取环上的下一个序列
        long sequence = ringBuffer.next();
        PCData data = ringBuffer.get(sequence);
        // 设置数据
        data.setValue(bb.getLong(0));
        // 发布序列
        ringBuffer.publish(sequence);
    }
}
