package org.mango.demo.selectnio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description 多路复用器版本 服务端
 * @Date 2021-10-03 13:40
 * @Created by mango
 */
public class SelectNIOServerDemo {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        int port = 9000;
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        System.out.println("server listen on port " + port);

        // 设置多路复用器
        Selector selector = Selector.open();
        // 将serverSocketChannel的accept事件注册到多路复用器
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            // 查看selector.select()是否有事件准备就绪，阻塞
            selector.select();
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            Iterator<SelectionKey> iterable = selectionKeySet.iterator();
            while(iterable.hasNext()){
                SelectionKey selectionKey = iterable.next();
                // 将事件remove掉，防止重复处理
                iterable.remove();
                if(selectionKey.isAcceptable()){
                    // 得到连接的通道
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    String socketName = socketChannel.toString();
                    System.out.println(socketName + " connect success!");
                    // 设置为非阻塞
                    socketChannel.configureBlocking(false);
                    // 注册读写事件到多路复用器
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    // socketChannel.register(selector,SelectionKey.OP_WRITE);
                }else if(selectionKey.isReadable()){
                    // 得到可读的通道
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    String socketName = socketChannel.toString();
                    try {
                        // 读取数据并回写数据给客户端
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int len = socketChannel.read(buffer);
                        if (len == -1) {
                            System.out.println(socketName + " closed!");
                        }
                        if (len > 0) {
                            ByteBuffer data = ByteBuffer.allocate(len);
                            buffer.flip();
                            data.put(buffer);
                            String sData = new String(data.array(), StandardCharsets.UTF_8);
                            System.out.println(socketName + " receive data:" + sData);
                            // 回写数据
                            String tn = Thread.currentThread().getName();
                            String rData = sData + " - " + tn;
                            socketChannel.write(ByteBuffer.wrap(rData.getBytes(StandardCharsets.UTF_8)));
                        }
                    }catch (Exception e){
                        // 异常推出
                        socketChannel.close();
                        System.out.println(socketName + " exception close!");
                    }
                }
            }
        }
    }
}
/**
 * 执行结果：
 * server listen on port 9000
 * java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:57934] connect success!
 * java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:57934] receive data:123
 * java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:57934] receive data:你好
 * java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:57934] receive data:中国
 * java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:58056] connect success!
 * java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:58056] receive data:你呀
 * java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:58056] receive data:搞毛线哦
 * java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:58056] receive data:exit
 * java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:58056] exception close!
 * java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:57934] receive data:exit
 * java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:57934] exception close!
 */
