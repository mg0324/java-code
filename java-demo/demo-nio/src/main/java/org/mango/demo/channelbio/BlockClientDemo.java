package org.mango.demo.channelbio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * @Description 同步阻塞的ServerSocketChannel示例 客服端
 * @Date 2021-10-01 11:01
 * @Created by mango
 */
public class BlockClientDemo {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        // 连接服务端
        String host = "127.0.0.1";
        int port = 9000;
        boolean isConnect = sc.connect(new InetSocketAddress(host,port));
        if(isConnect){
            System.out.println("connect server " + host + ":" + port + " success!");
            // 发送数据
            String input = "hello channelbio server!" + new Random().nextDouble();
            sc.write(ByteBuffer.wrap(input.getBytes(StandardCharsets.UTF_8)));
            System.out.println("send data success!");
            // 关闭通道
            sc.close();
        }
    }
}
/**
 * 运行结果：
 * connect server 127.0.0.1:9000 success!
 * send data success!
 */
