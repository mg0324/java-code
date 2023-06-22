package org.mango.demo.mt.question;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: mango
 * @Date: 2022/7/18 4:45 下午
 */
public class IntTest {
    public static void main(String[] args) {
        // int 4个字节，最大值为2147483647
        int a = 2147483641;
        int b = 2147483640;
        // -15，精度溢出
        System.out.println(a+b);

        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());

        Thread t = new Thread(()->{
            System.out.println("线程运行");
        });
        t.start();
    }
}
