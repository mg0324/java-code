package org.mango.demo.bio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Description TODO
 * @Date 2021-10-02 23:15
 * @Created by mango
 */
public class SocketClientDemo {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        // 连接服务端
        String host = "127.0.0.1";
        int port = 9000;
        socket.connect(new InetSocketAddress(host,port));
        System.out.println("connect server " + host + ":" + port + " success!");

        // 接收命令
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入exit:客户端退出；其他为发送数据!");
        // 发送数据
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        boolean loop = true;
        while(loop) {
            System.out.print("请输入：");
            String input = scanner.next();
            if("exit".equals(input)){
                socket.close();
                loop = false;
            }else{
                dos.writeUTF(input);
                System.out.println("send success,data:" + input);
                // 等待服务端发送数据,readUTF 方法会阻塞
                String sData = dis.readUTF();
                System.out.println("receive data:" + sData);
            }
        }
        System.out.println("client exit!");
    }
}
/**
 * 执行结果1:
 * connect server 127.0.0.1:9000 success!
 * 输入exit:客户端退出；其他为发送数据!
 * 请输入：你好
 * send success,data:你好
 * receive data:你好:Thread-0
 * 请输入：exit
 * client exit!
 */

/**
 * 执行结果2：
 * connect server 127.0.0.1:9000 success!
 * 输入exit:客户端退出；其他为发送数据!
 * 请输入：中国
 * send success,data:中国
 * receive data:中国:Thread-1
 * 请输入：exit
 * client exit!
 */
