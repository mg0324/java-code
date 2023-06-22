package com.mango.demo.future.future;

/**
 * 客户端
 * @Author: mango
 * @Date: 2022/7/27 11:27 下午
 */
public class Client {
    // 请求数据
    public FutureData request(String queryStr){
        FutureData futureData = new FutureData();
        // 开启线程异步出组装真实数据
        new Thread(()->{
            // 耗时操作
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 构建真实数据
            RealData realData = new RealData("hello future," + queryStr);
            futureData.setRealData(realData);
        }).start();
        // 立即返回
        return futureData;
    }
}
