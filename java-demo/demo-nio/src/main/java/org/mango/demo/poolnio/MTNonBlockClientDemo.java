package org.mango.demo.poolnio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @Date 2021-10-03 15:26
 * @Created by mango
 */
public class MTNonBlockClientDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel sc = SocketChannel.open();
        String host = "127.0.0.1";
        int port = 9000;
        sc.connect(new InetSocketAddress(host,port));
        // 设置非阻塞
        sc.configureBlocking(false);
        System.out.println("connect server " + host + ":" + port + " success!");
        // 从输入流中读取数据
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入exit:客户端退出；其他为发送数据!");
        boolean loop = true;
        while (loop){
            System.out.print("请输入:");
            String input = scanner.next();
            ByteBuffer data = ByteBuffer.wrap(input.getBytes(StandardCharsets.UTF_8));
            // 发送数据
            sc.write(data);
            if("exit".equals(input)){
                loop = false;
            }
        }
        System.out.println("client exit");
    }
}
/**
 * 执行结果1：
 * connect server 127.0.0.1:9000 success!
 * 输入exit:客户端退出；其他为发送数据!
 * 请输入:123
 * 请输入:你好
 * 请输入:exit
 * client exit
 */

/**
 * 执行结果2：
 * connect server 127.0.0.1:9000 success!
 * 输入exit:客户端退出；其他为发送数据!
 * 请输入:中国
 * 请输入:123
 * 请输入:exit
 * client exit
 */

