package org.mango.demo.bio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 1连接1线程模型（线程资源有限，不适用）
 * 将连进来的连接交给新的线程处理，这样主线程负责处理客户端连接，工作线程处理连接数据读取和写入
 * 支持客户端多连接
 * @Description socket server 编写的bio服务端 jdk1.0
 * @Date 2021-10-02 15:19
 * @Created by mango
 */
public class SocketServerDemo {
    public static void main(String[] args) throws IOException {
        // jdk1.0 版本，基于ServerSocket
        ServerSocket serverSocket = new ServerSocket();
        int port = 9000;
        serverSocket.bind(new InetSocketAddress(port));
        System.out.println("server listen on port " + port);
        while (true) {
            // 调用accept()方法，会阻塞
            Socket socket = serverSocket.accept();
            System.out.println(socket + " connect success!!");
            // 将连进来的连接交给新的线程处理，这样主线程负责处理客户端连接，工作线程处理连接数据读取和写入
            // 支持客户端多连接
            new WorkThread(socket).start();
        }
    }
}

/**
 * 工作线程
 */
class WorkThread extends Thread{
    private Socket socket;
    public WorkThread(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        String tn = Thread.currentThread().getName();
        String sn = socket.toString();
        // 将接收到的数据后跟 处理线程名称 发送给客户端
        boolean loop = true;
        while(loop){
            try {
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                // 从inputStream流读取数据，会阻塞
                String data = dis.readUTF();
                System.out.println(tn + ":" + sn + " receive data:" + data);
                String rData = data + ":" +tn;
                dos.writeUTF(rData);
            } catch (IOException e) {
                // 连接异常关闭，线程结束
                try {
                    socket.close();
                    loop = false;
                    System.out.println(tn + ":" + sn + " exception close!");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
/**
 * 执行结果：
 * server listen on port 9000
 * Socket[addr=/127.0.0.1,port=60357,localport=9000] connect success!!
 * Socket[addr=/127.0.0.1,port=60387,localport=9000] connect success!!
 * Thread-0:Socket[addr=/127.0.0.1,port=60357,localport=9000] receive data:你好
 * Thread-1:Socket[addr=/127.0.0.1,port=60387,localport=9000] receive data:中国
 * Thread-0:Socket[addr=/127.0.0.1,port=60357,localport=9000] exception close!
 * Thread-1:Socket[addr=/127.0.0.1,port=60387,localport=9000] exception close!
 */
