package com.mango.base.extend;
/**
 * 动物类
 */
public class Animal {
    protected String name;
    protected Integer age;
    protected String type;

    protected Animal(String name,Integer age){
        this.name = name;
        this.age = age;
    }
    public void cry(){
        System.out.println(age + "岁大的" + name + type + "开始哭泣了");
    }
}
