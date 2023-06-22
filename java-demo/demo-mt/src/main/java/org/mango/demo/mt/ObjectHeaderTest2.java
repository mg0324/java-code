package org.mango.demo.mt;

import org.openjdk.jol.info.ClassLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * 对象内存布局:
 *      对象头 + 属性值 + padding
 * 对象头又包含：
 *      markword + classpointer
 *
 * padding：是为了补齐对象内存是8字节的倍数，因为cpu的最新读取就是按8字节来读取，也就是64位
 */
public class ObjectHeaderTest2 {
    public static void main(String[] args) {


        int[] arr = new int[5];
        arr[0] = 1;
        arr[1] = 2;

        System.out.println(ClassLayout.parseInstance(arr).toPrintable());

        boolean[] a = new boolean[1];

        System.out.println(ClassLayout.parseInstance(a).toPrintable());

        char[] c = new char[1];
        System.out.println(ClassLayout.parseInstance(c).toPrintable());

    }
}
