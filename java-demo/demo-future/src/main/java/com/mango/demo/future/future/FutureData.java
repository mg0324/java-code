package com.mango.demo.other.future;

import java.util.concurrent.Future;
import java.util.concurrent.RunnableFuture;

/**
 * 数据的future凭证
 * @Author: mango
 * @Date: 2022/7/27 11:21 下午
 */
public class FutureData implements Data {
    // 是否准备好
    private boolean isReady;
    // 组装真实数据
    private RealData realData;

    @Override
    public synchronized String getResult() {
        // 没有准备好，则阻塞等待
        while (!isReady){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.getResult();
    }

    public synchronized void setRealData(RealData realData){
        if(isReady){
            return;
        }
        this.realData = realData;
        this.isReady = true;
        // 通知其他线程
        notifyAll();

    }
}
