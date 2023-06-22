package com.mango.base.extend;
/**
 * 猫类
 */
public class Cat extends Animal {

    protected Cat(String name, Integer age) {
        super(name, age);
        this.type = "猫猫";
    }
    
    @Override
    public void cry() {
        super.cry();
        System.out.println("喵喵喵！！！");
    }
}
