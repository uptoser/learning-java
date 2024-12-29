package com.uptoser.java.javase.other.net.tcp;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Java中能接收其他通信实体连接请求的类是ServerSocket，ServerSocket对象用于监听来自客户端的Socket连接，
 * 如果没有连接，它将一直处于等待状态。ServerSocket包含一个监听来自客户端连接请求的方法。
 * ➢ Socket accept()：如果接收到一个客户端Socket的连接请求，该方法将返回一个与客户端Socket对应的Socket,否则该方法将一直处于等待状态，线程也被阻塞。
 * 为了创建ServerSocket对象，ServerSocket类提供了如下几个构造器。
 * ➢ ServerSocket(int port)：用指定的端口port来创建一个ServerSocket。该端口应该有一个有效的端口整数值，即0～65535。
 * ➢ ServerSocket(int port, int backlog)：增加一个用来改变连接队列长度的参数backlog。
 * ➢ ServerSocket(int port, int backlog, InetAddress localAddr)：在机器存在多个IP地址的情况下，
 * 允许通过localAddr参数来指定将ServerSocket绑定到指定的IP地址。
 */
public class Server {
    public static void main(String[] args) throws IOException
    {
        // 创建一个ServerSocket，用于监听客户端Socket的连接请求
        ServerSocket ss = new ServerSocket(30000);
        // 采用循环不断接受来自客户端的请求
        while (true)
        {
            // 每当接受到客户端Socket的请求，服务器端也对应产生一个Socket
            Socket s = ss.accept();
            // 将Socket对应的输出流包装成PrintStream
            PrintStream ps = new PrintStream(s.getOutputStream());
            // 进行普通IO操作
            ps.println("您好，您收到了服务器的新年祝福！");
            // 关闭输出流，关闭Socket
            ps.close();
            s.close();
        }
    }
}
