package com.mango.serial;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * hessian 序列化测试
 * @Author: mango
 * @Date: 2023/2/12 4:55 PM
 */
public class HessianTest {
    public static void main(String[] args) throws IOException {
        String home = System.getProperty("user.home");
        String basePath = home + "/Desktop";
        System.out.println(basePath);
        FileOutputStream fos = new FileOutputStream(basePath + "/cat-hessian.bin");
        Cat cat = new Cat();
        cat.setName("皮侃子");
        cat.setAge(5);
        cat.setBirthday(new Date());
        // 序列化
        Hessian2Output output = new Hessian2Output(fos);
        long start = System.currentTimeMillis();
        output.writeObject(cat);
        System.out.println("hessian ser time:"+(System.currentTimeMillis()-start));
        output.flushBuffer();
        output.close();

        // 反序列化
        FileInputStream fis = new FileInputStream(basePath + "/cat-hessian.bin");
        Hessian2Input input = new Hessian2Input(fis);
        long start2 = System.currentTimeMillis();
        Cat pkz = (Cat) input.readObject();
        System.out.println("hessian deser time:"+(System.currentTimeMillis()-start2));
        input.close();
        System.out.println(pkz);
    }
}
