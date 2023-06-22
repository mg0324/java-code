package main.java.com.mango.serial;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Protosutff 测试
 * @Author: mango
 * @Date: 2023/2/12 6:54 PM
 */
public class ProtostuffTest {
    public static void main(String[] args) throws IOException {
        String home = System.getProperty("user.home");
        String basePath = home + "/Desktop";
        System.out.println(basePath);
        FileOutputStream fos = new FileOutputStream(basePath + "/cat-protostuff.bin");

        Cat cat = new Cat();
        cat.setName("皮侃子");
        cat.setAge(3);
        cat.setBirthday(new Date());

        Schema<Cat> schema = RuntimeSchema.getSchema(Cat.class);
        LinkedBuffer buffer = LinkedBuffer.allocate(512);

        // 序列化
        final byte[] protostuff;
        try {
            long start = System.currentTimeMillis();
            protostuff = ProtostuffIOUtil.toByteArray(cat, schema, buffer);
            System.out.println("ser time:"+(System.currentTimeMillis()-start));
        }finally {
            buffer.clear();
        }
        fos.write(protostuff);
        fos.flush();
        fos.close();

        // 反序列化
        FileInputStream fis = new FileInputStream(basePath + "/cat-protostuff.bin");
        byte[] data = new byte[fis.available()];
        fis.read(data);
        fis.close();

        Cat catParsed = schema.newMessage();
        long start = System.currentTimeMillis();
        ProtostuffIOUtil.mergeFrom(data, catParsed, schema);
        System.out.println("deser time:"+(System.currentTimeMillis()-start));
        System.out.println(catParsed);
    }
}
