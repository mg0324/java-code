package com.mango.base.extend;

public class Main {
    public static void main(String[] args) {
        Animal dog = new Dog("小黑",2);
        dog.cry();
        
        Animal cat = new Cat("皮侃子",3);
        cat.cry();
    }
}
