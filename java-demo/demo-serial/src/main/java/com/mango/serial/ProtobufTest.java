package main.java.com.mango.serial;

import com.google.protobuf.util.Timestamps;
import java.io.*;
import java.util.Date;

/**
 * protobuf测试
 * @Author: mango
 * @Date: 2023/2/12 5:41 PM
 */
public class ProtobufTest {
    public static void main(String[] args) throws IOException {
        String home = System.getProperty("user.home");
        String basePath = home + "/Desktop";
        System.out.println(basePath);
        FileOutputStream fos = new FileOutputStream(basePath + "/cat-protobuf.bin");

        CatProtobuf.CatMsg.Builder builder = CatProtobuf.CatMsg.newBuilder();
        builder.setAge(4);
        builder.setName("皮侃子");
        builder.setBirthday(Timestamps.fromMillis(new Date().getTime()));

        // 序列化
        CatProtobuf.CatMsg catMsg = builder.build();
        long start = System.currentTimeMillis();
        fos.write(catMsg.toByteArray());
        System.out.println("protobuf ser time:"+(System.currentTimeMillis()-start));
        fos.flush();
        fos.close();

        // 反序列化
        FileInputStream fis = new FileInputStream(basePath + "/cat-protobuf.bin");
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        long start2 = System.currentTimeMillis();
        CatProtobuf.CatMsg pkzMsg = CatProtobuf.CatMsg.parseFrom(buffer);
        System.out.println("protobuf deser time:"+(System.currentTimeMillis()-start2));
        System.out.println(pkzMsg.getName());
        System.out.println(pkzMsg.getAge());
        System.out.println(pkzMsg.getBirthday());
        System.out.println(new Date(pkzMsg.getBirthday().getSeconds() * 1000).toLocaleString());
    }
}
