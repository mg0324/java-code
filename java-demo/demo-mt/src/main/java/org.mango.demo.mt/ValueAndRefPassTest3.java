package org.mango.demo.mt;


/**
 * 值传递和引用传递
 * @Author: mango
 * @Date: 2022/6/11 11:24 上午
 */
public class ValueAndRefPassTest3 {
    public static void main(String[] args) {
        String str = "123";
        changeStr(str);
        System.out.println(str);
    }

    private static void changeStr(String a){
        a = "100";
    }

}

