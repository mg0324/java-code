package org.mango.demo.mt;


/**
 * 值传递和引用传递
 * @Author: mango
 * @Date: 2022/6/11 11:24 上午
 */
public class ValueAndRefPassTest2 {
    public static void main(String[] args) {
        int num = 1;
        changeNum(num);
        System.out.println(num);
    }

    public static void changeNum(int a){
        a = 100;
    }

}

