package main.java.com.mango.serial;

import java.io.*;
import java.util.Date;

/**
 * jdk序列化测试
 * @Author: mango
 * @Date: 2023/2/12 3:48 PM
 */
public class JDKSerialTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String home = System.getProperty("user.home");
        String basePath = home + "/Desktop";
        System.out.println(basePath);
        FileOutputStream fos = new FileOutputStream(basePath + "/cat-jdk.bin");
        Cat cat = new Cat();
        cat.setName("皮侃子");
        cat.setAge(6);
        cat.setBirthday(new Date());
        ObjectOutputStream bos = new ObjectOutputStream(fos);
        long start = System.currentTimeMillis();
        bos.writeObject(cat);
        System.out.println("jdk ser time:"+(System.currentTimeMillis()-start));
        bos.flush();
        bos.close();

        FileInputStream fis = new FileInputStream(basePath + "/cat-jdk.bin");
        ObjectInputStream bis = new ObjectInputStream(fis);
        long start2 = System.currentTimeMillis();
        Cat pkz  = (Cat) bis.readObject();
        System.out.println("jdk deser time:"+(System.currentTimeMillis()-start2));
        bis.close();
        System.out.println(pkz);
    }
}
