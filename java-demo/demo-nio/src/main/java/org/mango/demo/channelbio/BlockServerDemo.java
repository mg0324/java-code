package org.mango.demo.channelbio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @Description 同步阻塞的ServerSocketChannel示例 服务端
 * @Date 2021-10-01 11:01
 * @Created by mango
 */
public class BlockServerDemo {
    public static void main(String[] args) throws IOException {
        // 服务端 使用ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        int port = 9000;
        // 监听本地端口
        serverSocketChannel.bind(new InetSocketAddress(port));
        System.out.println("server is listen port " + port);

        // accept方法阻塞，获取一个连接，直到有连接连进来
        SocketChannel sc = serverSocketChannel.accept();
        System.out.println("client connect success !" + sc.toString());

        // read方法阻塞，读取数据，直到有数据
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int size = sc.read(buffer);
        System.out.println("receive data size is " + size);
        // 创建数据长度一致的byteBuf
        ByteBuffer data = ByteBuffer.allocate(size);
        // 翻转缓冲区，使得能被put
        buffer.flip();
        data.put(buffer);
        System.out.println("receive data is " + new String(data.array(), StandardCharsets.UTF_8));
        sc.close();
        serverSocketChannel.close();
    }
}
/**
 * 执行结果：
 * server is listen port 9000
 * client connect success !java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:56827]
 * receive data size is 37
 * receive data is hello channelbio server!0.9110399517988187
 */
