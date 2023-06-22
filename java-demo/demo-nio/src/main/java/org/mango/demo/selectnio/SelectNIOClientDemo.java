package org.mango.demo.selectnio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @Date 2021-10-03 15:26
 * @Created by mango
 */
public class SelectNIOClientDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel sc = SocketChannel.open();
        String host = "127.0.0.1";
        int port = 9000;
        sc.connect(new InetSocketAddress(host,port));
        // 设置非阻塞
        sc.configureBlocking(false);
        System.out.println("connect server " + host + ":" + port + " success!");

        // 设置多路复用器，注册读事件
        Selector selector = Selector.open();
        sc.register(selector, SelectionKey.OP_READ);
        sc.register(selector, SelectionKey.OP_WRITE);
        // 从输入流中读取数据
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入exit:客户端退出；其他为发送数据!");
        boolean loop = true;
        while (loop){
            // 查看多路复用器
            selector.select();
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeySet.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                // 移除掉事件，防止重复处理
                iterator.remove();
                if(selectionKey.isWritable()){
                    System.out.print("请输入:");
                    String input = scanner.next();
                    ByteBuffer data = ByteBuffer.wrap(input.getBytes(StandardCharsets.UTF_8));
                    // 发送数据
                    sc.write(data);
                    if("exit".equals(input)){
                        loop = false;
                    }
                }else if(selectionKey.isReadable()){
                    // 得到可读的通道
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    String socketName = socketChannel.toString();
                    // 读取数据并回写数据给客户端
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int len = socketChannel.read(buffer);
                    if(len == -1){
                        System.out.println(socketName + " closed!");
                    }
                    if(len > 0){
                        ByteBuffer data = ByteBuffer.allocate(len);
                        buffer.flip();
                        data.put(buffer);
                        String sData = new String(data.array(), StandardCharsets.UTF_8);
                        System.out.println(socketName + " receive data:" + sData);
                    }
                }
            }
        }
        // 关闭资源
        selector.close();
        sc.close();
        System.out.println("client exit");
    }
}
/**
 * 执行结果1：
 * connect server 127.0.0.1:9000 success!
 * 输入exit:客户端退出；其他为发送数据!
 * 请输入:你呀
 * 请输入:搞毛线哦
 * 请输入:exit
 * client exit
 */

/**
 * 执行结果2：
 * connect server 127.0.0.1:9000 success!
 * 输入exit:客户端退出；其他为发送数据!
 * 请输入:123
 * 请输入:你好
 * 请输入:中国
 * 请输入:exit
 * client exit
 */

