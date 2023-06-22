package org.mango.demo.mt;

public class VolatileSingleton {
    public static volatile VolatileSingleton instance;
    public static VolatileSingleton getInstance(){
        if(instance == null){
            synchronized(VolatileSingleton.class){
                if(instance== null){
                    instance = new VolatileSingleton();
                }
            }
        }
        return instance;
    }
    public static void main(String[] args) {
        VolatileSingleton.getInstance();
    }
}