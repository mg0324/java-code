package com.mango.demo.other.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * 对象实例工厂
 * @Author: mango
 * @Date: 2022/7/26 12:29 下午
 */
public class PCDataFactory implements EventFactory<PCData> {
    @Override
    public PCData newInstance() {
        return new PCData();
    }
}
