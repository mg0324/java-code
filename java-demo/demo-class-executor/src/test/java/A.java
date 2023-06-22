import sun.rmi.rmic.iiop.ValueType;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: mango
 * @Date: 2022/9/2 7:43 下午
 */
public class A {
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        System.out.println("result="+(a+b));

        MemoryMXBean bean = ManagementFactory.getMemoryMXBean();
        System.out.println(bean.getHeapMemoryUsage().toString());

        List list = new ArrayList<String>();
        list.add("1");
        list.add(1);
        list.add(10l);
        list.add(2.3f);
        list.add(2.3d);
        list.forEach(System.out::println);
        String xxx = "111";

    }
}
