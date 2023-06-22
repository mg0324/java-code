package com.mango.demo.other.future;

/**
 * @Author: mango
 * @Date: 2022/7/27 11:33 下午
 */
public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        FutureData futureData = client.request("测试");
        // 做其他的事情
        System.out.println("做其他事情1");
        System.out.println("做其他事情2");
        // 获取结果
        String result = futureData.getResult();
        System.out.println("异步结果为："+result);
    }
}
