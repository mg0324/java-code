package org.mango.demo.mt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 对象引用多线程测试
 * @Author: mango
 * @Date: 2022/6/11 7:16 下午
 */
public class ObjectRefMTTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2);
        List<Handler> handlerList = new ArrayList<>();
        for(int i=0;i<20;i++) {
            handlerList.add(new Handler("a"+i, "a"+i));
        }
        List<Future> futureList = new ArrayList<>();
        for(Handler handler : handlerList) {
            Future<?> future = executorService.submit(() -> {
                Handler handler1 = handler;
                try {
                    int sleep = (int) (Math.random() * 1000);
                    Thread.sleep(sleep);
                    System.out.println(Thread.currentThread().getName() + " handler is " + handler1 + " sleep "+sleep+"ms");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            futureList.add(future);
        }
        for(Future future : futureList){
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("处理完成");
        executorService.shutdown();
    }
}

class Handler{
    private String name;
    private Object data;

    public Handler(String name, Object data) {
        this.name = name;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Handler{" +
                "name='" + name + '\'' +
                ", data=" + data +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
