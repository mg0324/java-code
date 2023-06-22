package com.mango.demo.other.future;

/**
 * 真实数据
 * @Author: mango
 * @Date: 2022/7/27 11:20 下午
 */
public class RealData implements Data{
    private String content;
    public RealData(String content){
        this.content = content;
    }
    @Override
    public String getResult() {
        return content;
    }
}
