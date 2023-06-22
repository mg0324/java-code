package org.mango.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description （服务端非阻塞）主动遍历 一个线程处理客服端连接，数据读取
 * @Date 2021-10-01 15:26
 * @Created by mango
 */
public class NonBlockServerDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 存放连接进来的SocketChannel
        List<SocketChannel> scList = new ArrayList<>();
        // 服务端，使用ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        int port = 9000;
        // 绑定本地端口
        serverSocketChannel.bind(new InetSocketAddress(port));
        System.out.println("server listen on " + port);
        // 设置服务端通道非阻塞
        serverSocketChannel.configureBlocking(false);

        while (true){
            // accept方法非阻塞，立即返回，null表示没有client连接进来
            SocketChannel sc = serverSocketChannel.accept();
            if(null == sc){
                Thread.sleep(200);
            }else{
                System.out.println(Thread.currentThread().getName() + " client enter " + sc.toString());
                // 设置连接非阻塞
                sc.configureBlocking(false);
                // 维护到集合中
                scList.add(sc);
            }
            // 遍历连接，读取数据
            for(SocketChannel temp : scList){
                // 创建一个缓存区
                ByteBuffer buffer = ByteBuffer.allocate(1 * 1024);
                // read方法非阻塞，返回值为数据长度
                int len = temp.read(buffer);
                String socketName = temp.toString();
                if(len > 0){
                    System.out.println(Thread.currentThread().getName() + " " + socketName + " receive data size is " + len);
                    ByteBuffer data = ByteBuffer.allocate(len);
                    // 翻转缓冲区，使得能被put
                    buffer.flip();
                    data.put(buffer);
                    System.out.println(Thread.currentThread().getName() + " " + socketName + " receive data is " + new String(data.array(), StandardCharsets.UTF_8));
                }
            }
        }
    }
}
/**
 * 执行结果：
 * server listen on 9000
 * main client enter java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:50039]
 * main java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:50039] receive data size is 3
 * main java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:50039] receive data is 123
 * main java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:50039] receive data size is 6
 * main java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:50039] receive data is 你好
 * main java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:50039] receive data size is 6
 * main java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:50039] receive data is 中国
 * main client enter java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:50308]
 * main java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:50308] receive data size is 3
 * main java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:50308] receive data is 123
 * main java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:50308] receive data size is 3
 * main java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:50308] receive data is xxx
 * main java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:50039] receive data size is 4
 * main java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:50039] receive data is exit
 * main java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:50308] receive data size is 4
 * main java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:50308] receive data is exit
 */
