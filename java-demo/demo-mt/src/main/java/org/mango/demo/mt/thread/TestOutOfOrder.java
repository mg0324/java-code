package org.mango.demo.mt.thread;

/**
 * 指令重排序测试
 * 第332129次 (0,0）
 */
public class TestOutOfOrder {
    private static int x = 0, y = 0;
    private static int a = 0, b =0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for(;;) {
            i++;
            x = 0; y = 0;
            a = 0; b = 0;
            Thread one = new Thread(() -> {
                    a = 1;
                    x = b;
            });

            Thread other = new Thread(() -> {
                    b = 1;
                    y = a;
            });
            one.start();other.start();
            one.join();other.join();
            String result = "第" + i + "次 (" + x + "," + y + "）";
            if(x == 0 && y == 0) {
                System.err.println(result);
                break;
            } else {
                //System.out.println(result);
            }
        }
    }
}
