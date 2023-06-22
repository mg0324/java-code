package org.mango.demo;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Description
 * @Date 2021-09-27 22:24
 * @Created by mango
 */
public class FileChannelDemo {

    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("//Users/mango/git/java-study/temp/test.txt","rw");
        FileChannel fc = raf.getChannel();
        byte[] data = "您好!".getBytes("utf-8");
        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE,raf.length(),data.length);
        System.out.println(mbb);
        fc.position(raf.length());
        mbb.put(data);
        fc.write(ByteBuffer.wrap("hello FileChannel!".getBytes("utf-8")));
        fc.force(true);
    }
}
