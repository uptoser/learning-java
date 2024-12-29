package com.uptoser.java.javase.other.net.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 客户端通常可以使用Socket的构造器来连接到指定服务器，Socket通常可以使用如下两个构造器。
 * ➢ Socket(InetAddress/String remoteAddress, int port)：创建连接到指定远程主机、远程端口的Socket，
 * 该构造器没有指定本地地址、本地端口，默认使用本地主机的默认IP地址，默认使用系统动态分配的端口。
 * ➢ Socket(InetAddress/String remoteAddress, int port, InetAddress localAddr, int localPort)：
 * 创建连接到指定远程主机、远程端口的Socket，并指定本地IP地址和本地端口，适用于本地主机有多个IP地址的情形。
 *
 * 上面两个构造器中指定远程主机时既可使用InetAddress来指定，也可直接使用String对象来指定，
 * 但程序通常使用String对象（如192.168.2.23）来指定远程IP地址。当本地主机只有一个IP地址时，使用第一个方法更为简单
 *
 * Socket提供了如下两个方法来获取输入流和输出流。
 * ➢ InputStream getInputStream()：返回该Socket对象对应的输入流，让程序通过该输入流从Socket中取出数据。
 * ➢ OutputStream getOutputStream()：返回该Socket对象对应的输出流，让程序通过该输出流向Socket中输出数据。
 */
public class Client {
    public static void main(String[] args) throws IOException
    {
        Socket socket = new Socket("127.0.0.1" , 30000);   //①
        /*
        在实际应用中，程序可能不想让执行网络连接、读取服务器数据的进程一直阻塞，
        而是希望当网络连接、读取操作超过合理时间之后，系统自动认为该操作失败，这个合理时间就是超时时长。
        Socket对象提供了一个setSoTimeout（int timeout）方法来设置超时时长
        超出了该时间限制就会抛出SocketTimeoutException异常
         */
        socket.setSoTimeout(10000);
        // 将Socket对应的输入流包装成BufferedReader
        BufferedReader br = new BufferedReader( new InputStreamReader(socket.getInputStream()));
        // 进行普通IO操作
        String line = br.readLine();
        System.out.println("来自服务器的数据：" + line);
        // 关闭输入流、socket
        br.close();
        socket.close();
    }
}
