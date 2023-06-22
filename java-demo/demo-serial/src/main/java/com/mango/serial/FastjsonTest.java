package com.mango.serial;

import com.alibaba.fastjson2.JSONObject;
import java.io.*;
import java.util.Date;

/**
 * @Author: mango
 * @Date: 2023/2/12 5:15 PM
 */
public class FastjsonTest {
    public static void main(String[] args) throws IOException {
        String home = System.getProperty("user.home");
        String basePath = home + "/Desktop";
        System.out.println(basePath);
        FileWriter fw = new FileWriter(basePath + "/cat-fastjson.bin");
        Cat cat = new Cat();
        cat.setName("皮侃子");
        cat.setAge(5);
        cat.setBirthday(new Date());
        // 序列化
        long start = System.currentTimeMillis();
        fw.write(JSONObject.toJSONString(cat));
        System.out.println("fastjson2 ser time:"+(System.currentTimeMillis()-start));
        fw.flush();
        fw.close();

        // 反序列化
        FileReader fr = new FileReader(basePath + "/cat-fastjson.bin");
        BufferedReader br = new BufferedReader(fr);
        StringBuilder sb = new StringBuilder();
        String row = null;
        while ((row = br.readLine()) != null){
            sb.append(row);
        }
        br.close();
        long start2 = System.currentTimeMillis();
        Cat pkz = JSONObject.parseObject(sb.toString(),Cat.class);
        System.out.println("fastjson2 deser time:"+(System.currentTimeMillis()-start2));
        System.out.println(pkz);
    }
}
