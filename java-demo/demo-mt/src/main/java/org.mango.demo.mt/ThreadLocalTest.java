package org.mango.demo.mt;

import java.nio.ByteBuffer;

public class ThreadLocalTest {
    public static void main(String[] args) {
        TL tl1 = new TL();
        tl1.setTl("tl1");

        TL tl2 = new TL();
        tl2.setTl("tl2");

        tl1.start();
        tl2.start();
    }


}

class TL extends Thread{
    private ThreadLocal<String> tl = new ThreadLocal<>();
    public void setTl(String value){
        tl.set(value);
    }

    @Override
    public void run() {
        super.run();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(currentThread().getName()+" tl.value="+tl.get());
    }
}
