package com.mango.demo.future.final2;

/**
 * 不变猫对象
 * @Author: mango
 * @Date: 2022/7/26 11:01 下午
 */
public final class FinalCat {// final 确保无子类
    private final String name;// final 确保属性
    private final Integer age;

    public FinalCat(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "FinalCat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        FinalCat cat = new FinalCat("皮侃子", 4);
        System.out.println(cat);
    }
}
