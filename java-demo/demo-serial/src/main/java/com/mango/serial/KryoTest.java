package com.mango.serial;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.serializers.BeanSerializer;
import com.esotericsoftware.kryo.serializers.JavaSerializer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * kyro序列化测试
 * @Author: mango
 * @Date: 2023/2/12 7:05 PM
 */
public class KryoTest {
    public static void main(String[] args) throws FileNotFoundException {
        String home = System.getProperty("user.home");
        String basePath = home + "/Desktop";
        System.out.println(basePath);
        FileOutputStream fos = new FileOutputStream(basePath + "/cat-kryo.bin");

        Kryo kryo = new Kryo();
        kryo.register(Cat.class, new JavaSerializer());

        Cat cat = new Cat();
        cat.setName("皮侃子");
        cat.setAge(2);
        cat.setBirthday(new Date());
        // 序列化
        Output output = new Output(fos);
        kryo.writeObject(output, cat);
        output.close();

        // 反序列化
        Input input = new Input(new FileInputStream(basePath + "/cat-kryo.bin"));
        Cat pkz = kryo.readObject(input, Cat.class);
        input.close();
        System.out.println(pkz);

    }
}

