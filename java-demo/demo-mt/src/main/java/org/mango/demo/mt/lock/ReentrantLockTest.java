package org.mango.demo.mt.lock;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.*;

/**
 * 重入锁测试
 * @Author: mango
 * @Date: 2022/7/13 10:26 上午
 */
public class ReentrantLockTest {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Object lock2 = new Object();

        synchronized (lock2){
            lock2.wait();
        }

        synchronized (lock2){
            lock2.notify();
        }

        Condition cond1 = lock.newCondition();
        lock.lock();
        cond1.await();
        lock.unlock();

        lock.lock();
        cond1.signal();
        lock.unlock();
        Condition cond2 = lock.newCondition();

        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Lock readLock = readWriteLock.readLock();// 读锁，共享锁
        Lock writeLock = readWriteLock.writeLock();// 写锁，独占锁

        CountDownLatch countDownLatch = new CountDownLatch(3);
        // 注意：相关检查是多线程并发执行的
        // 检查1
        // doCheck1()
        countDownLatch.countDown();
        // 检查2
        // doCheck2()
        countDownLatch.countDown();
        // 检查3
        // doCheck3()
        countDownLatch.countDown();
        // 等待检查完成，再执行发射逻辑
        countDownLatch.await();
        // 点火发射
        // doFire()

        LockSupport.park();
        LockSupport.unpark(Thread.currentThread());


    }
}
// 类T
class T {
    // 静态对象，是在类上的，只有一个
    private static Object lock = new Object();

    @SneakyThrows
    public void doSomething(){
        // 锁静态对象，只有一个，是系统一个锁
        synchronized (lock){
            lock.wait();
        }
    }
    public void doSomething2(){
        Class tClass = T.class;
        // 锁类对象，只有一个，是同一个锁
        synchronized (tClass){
           // 做一些事情
            lock.notify();
        }
    }
    // 修饰在实例方法上，实则是在调用对象上加锁，调用对象不同则锁不同
    public synchronized void doSomething3(){

    }

    // 修饰在静态方法上，实则是在当前类对象上加锁，只有一个锁
    public static synchronized void doSomething4(){

    }

    public String concatString(String s1,
                               String s2,
                               String s3){
        return s1 + s2 + s3;
    }

    public String concatString2(String s1,
                                String s2,
                                String s3){
        StringBuffer sb = new StringBuffer();
        sb.append(s1);// synchronized append(String str)
        sb.append(s2);
        sb.append(s3);
        return sb.toString();
    }

    public static void main(String[] args) {
        Object lock = new Object();
        synchronized (lock) {
            for (int i = 0; i < 100; i++) {
                // do sth

            }
        }
        synchronized (lock){
            // do sth
        }
    }

}
