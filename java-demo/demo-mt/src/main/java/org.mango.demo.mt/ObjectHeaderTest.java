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
public class ObjectHeaderTest {
    public static void main(String[] args) {
        Object obj = new Object();
        /**
         * java.lang.Object object internals:
         *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
         *       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4        (object header)                           e5 01 00 f8 (11100101 00000001 00000000 11111000) (-134217243)
         *      12     4        (loss due to the next object alignment)
         * Instance size: 16 bytes
         * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
         */
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        String str = new String("abc");
        /**
         * java.lang.String object internals:
         *  OFFSET  SIZE     TYPE DESCRIPTION                               VALUE
         *       0     4          (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         *       4     4          (object header)             å              00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4          (object header)                           da 02 00 f8 (11011010 00000010 00000000 11111000) (-134216998)
         *      12     4   char[] String.value                              []
         *      16     4      int String.hash                               0
         *      20     4          (loss due to the next object alignment)
         * Instance size: 24 bytes
         * Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
         */
        System.out.println(ClassLayout.parseInstance(str).toPrintable());

        Map map = new HashMap();
        System.out.println(ClassLayout.parseInstance(map).toPrintable());
        map.put("a","a");
        System.out.println(ClassLayout.parseInstance(map).toPrintable());

        int[] arr = new int[3];
        arr[0] = 1;
        arr[1] = 2;
        System.out.println(ClassLayout.parseInstance(arr).toPrintable());

        Short s = 1;
        System.out.println(ClassLayout.parseInstance(s).toPrintable());

        boolean b = true;
        System.out.println(ClassLayout.parseInstance(b).toPrintable());

        char c = '1';
        System.out.println(ClassLayout.parseInstance(c).toPrintable());

        char c1 = '中';
        System.out.println(ClassLayout.parseInstance(c1).toPrintable());
    }
}
