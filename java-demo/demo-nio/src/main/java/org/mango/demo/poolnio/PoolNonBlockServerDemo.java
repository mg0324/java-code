package org.mango.demo.poolnio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description （服务端非阻塞）主动遍历 一线程1连接，数据读取,线程池版本
 * @Date 2021-10-01 15:26
 * @Created by mango
 */
public class PoolNonBlockServerDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 创建工作线程池
        ExecutorService executorService = Executors.newFixedThreadPool(8);
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
                // 提交到线程池
                executorService.submit(new WorkThread(sc));
            }
        }
    }
}

/**
 * 工作线程
 */
class WorkThread extends Thread{
    private SocketChannel socketChannel;
    public WorkThread(SocketChannel socketChannel){
        this.socketChannel = socketChannel;
    }
    @Override
    public void run() {
        boolean loop = true;
        String tn = Thread.currentThread().getName();
        String sn = socketChannel.toString();
        try{
            // 将接收到的数据后跟 处理线程名称 发送给客户端
            while(loop){
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                // read() 不会阻塞
                int len = socketChannel.read(buffer);
                if(len > 0) {
                    System.out.println(tn + ":" + sn + " receive data size :" + len);
                    ByteBuffer data = ByteBuffer.allocate(len);
                    // 翻转缓冲区，使得能被put
                    buffer.flip();
                    data.put(buffer);
                    String sData = new String(data.array(),StandardCharsets.UTF_8);
                    System.out.println(tn + ":" + sn + " receive data:" + sData);
                    String rData = sData + ":" + tn;
                    socketChannel.write(ByteBuffer.wrap(rData.getBytes(StandardCharsets.UTF_8)));
                }
            }
        } catch (IOException e) {
            // 异常退出
            try {
                socketChannel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.out.println(tn + ":" + sn + " exception close!");
        }
    }
}

/**
 * 执行结果：
 * server listen on 9000
 * main client enter java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:54386]
 * Thread-0:java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:54386] receive data size :3
 * Thread-0:java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:54386] receive data:123
 * Thread-0:java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:54386] receive data size :6
 * Thread-0:java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:54386] receive data:你好
 * main client enter java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:54480]
 * Thread-1:java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:54480] receive data size :6
 * Thread-1:java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:54480] receive data:中国
 * Thread-1:java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:54480] receive data size :3
 * Thread-1:java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:54480] receive data:123
 * Thread-1:java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:54480] receive data size :4
 * Thread-1:java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:54480] receive data:exit
 * Thread-1:java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:54480] exception close!
 * Thread-0:java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:54386] receive data size :4
 * Thread-0:java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:54386] receive data:exit
 * Thread-0:java.nio.channels.SocketChannel[connected local=/127.0.0.1:9000 remote=/127.0.0.1:54386] exception close!
 *
 *
 */