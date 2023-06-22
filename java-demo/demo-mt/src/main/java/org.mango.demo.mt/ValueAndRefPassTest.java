package org.mango.demo.mt;

import org.openjdk.jol.vm.VM;

/**
 * 值传递和引用传递
 * @Author: mango
 * @Date: 2022/6/11 11:24 上午
 */
public class ValueAndRefPassTest {
    public static void main(String[] args) {
        System.out.println("--------int start----------");
        // int为基本类型，是值传递，value还是等于100
        int value = 100;
        System.out.println("value address:"+VM.current().addressOf(value));
        changeNum(value);
        System.out.println("value:"+value);
        System.out.println("value address:"+VM.current().addressOf(value));
        System.out.println("--------int end----------");
        System.out.println("--------String start----------");
        String str = "123";
        System.out.println("String address:"+VM.current().addressOf(str));
        changeStr(str);
        System.out.println("String value:"+str);
        System.out.println("String address:"+VM.current().addressOf(str));
        System.out.println("--------String end----------");

        System.out.println("--------A.class start----------");
        A a = new A(12,"123");
        System.out.println("A.class address:"+VM.current().addressOf(a));
        changeA(a);
        System.out.println("A.class value:"+a);
        System.out.println("A.class address:"+VM.current().addressOf(a));
        System.out.println("--------A.class end----------");

        System.out.println(VM.current().details());
    }

    private static void changeNum(int a){
        System.out.println("method value address before:"+VM.current().addressOf(a));
        a = 1;
        System.out.println("method value address after:"+VM.current().addressOf(a));
    }

    private static void changeStr(String str){
        System.out.println("method String address before:"+VM.current().addressOf(str));
        str = "234";
        System.out.println("method String address after:"+VM.current().addressOf(str));
    }

    private static void changeA(A a){
        System.out.println("method A.class address before:"+VM.current().addressOf(a));
        a.age = 1;
        a.name = "xxx";
        System.out.println("method A.class address after:"+VM.current().addressOf(a));
    }

    static class A{
        public int age;
        public String name;

        public A(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "A{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}

