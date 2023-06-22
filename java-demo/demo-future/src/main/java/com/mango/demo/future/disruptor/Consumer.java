package com.mango.demo.future.disruptor;

import com.lmax.disruptor.WorkHandler;

/**
 * 消费者
 * @Author: mango
 * @Date: 2022/7/26 12:14 下午
 */
public class Consumer implements WorkHandler<PCData> {

    @Override
    public void onEvent(PCData pcData) throws Exception {
        // 打印平方值
        System.out.println(Thread.currentThread().getName() +
                " -- value="+pcData.getValue() +
                " -- 平方="+Math.pow(pcData.getValue(),2));
    }
}
