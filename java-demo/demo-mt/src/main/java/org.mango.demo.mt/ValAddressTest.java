package org.mango.demo.mt;

import org.openjdk.jol.vm.VM;

/**
 * 变量地址
 * @Author: mango
 * @Date: 2022/6/11 11:44 下午
 */
public class ValAddressTest {
    public static void main(String[] args) {
        A a = new A("123");
        System.out.println(a);
        System.out.println(Long.toHexString(VM.current().addressOf(a.hashCode())));
        System.out.println(Long.toHexString(a.hashCode()));
    }

    static class A extends Object{
        private String name;

        public A(String name) {
            this.name = name;
        }
    }
}