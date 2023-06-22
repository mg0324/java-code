package org.mango.demo.mt;

/**
 * double check lock 双重锁校验 单例模式
 */
public class DCLCheckTest {
    public static void main(String[] args) {
        for(int i=1;i<10000;i++){
            new Thread(()->{
                System.out.println(SingletTon.getInstance().hashCode());
            }).start();
        }
    }
}

class SingletTon {
    // volatile 防止new对象时多核cpu的指令重排序
    private static volatile SingletTon instance = null;
    // 私有化构造方法，防止别人创建对象
    private SingletTon(){}
    public static SingletTon getInstance(){
        // 先检查下对象是否为空
        if(null == instance) {
            // 防止有2个线程同时进来去，所以加锁
            synchronized (SingletTon.class) {
                // 加锁后再次判断对象是否为空
                if(null == instance){
                    instance = new SingletTon();
                }
            }
        }
        return instance;
    }
}
