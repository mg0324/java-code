package org.mango.demo.mt;

/**
 * 自动装箱与自动拆箱
 * @Author: mango
 * @Date: 2022/6/12 3:52 下午
 */
public class BoxAndUnboxTest {
    public static void main(String[] args) {
        Integer num = 1; // 自动装箱 Integer.valueOf(1)
        int a = num; // 自动拆箱 num.intValue()
    }
}
