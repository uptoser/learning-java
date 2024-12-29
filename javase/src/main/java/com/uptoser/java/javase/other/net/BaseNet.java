package com.uptoser.java.javase.other.net;

import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;


public class BaseNet {
    /**
     * Java提供了InetAddress类来代表IP地址，InetAddress下还有两个子类：Inet4Address、Inet6Address，
     * 它们分别代表Internet Protocol version 4（IPv4）地址和Internet Protocol version 6（IPv6）地址。
     * InetAddress类没有提供构造器，而是提供了如下两个静态方法来获取InetAddress实例。
     * ➢ getByName(String host)：根据主机获取对应的InetAddress对象。
     * ➢ getByAddress(byte[] addr)：根据原始IP地址来获取对应的InetAddress对象。
     *
     * InetAddress还提供了如下三个方法来获取InetAddress实例对应的IP地址和主机名。
     * ➢ String getCanonicalHostName()：获取此IP地址的全限定域名。
     * ➢ String getHostAddress()：返回该InetAddress实例对应的IP地址字符串（以字符串形式）。
     * ➢ String getHostName()：获取此IP地址的主机名。
     */
    @Test
    public void getBase() throws IOException {
        // 根据主机名来获取对应的InetAddress实例
        InetAddress ip = InetAddress.getByName("www.crazyit.org");
        // 判断是否可达
        System.out.println("crazyit是否可达：" + ip.isReachable(2000));
        // 获取该InetAddress实例的IP字符串
        System.out.println(ip.getHostAddress());
        // 根据原始IP地址来获取对应的InetAddress实例
        InetAddress local = InetAddress.getByAddress(
                new byte[]{127,0,0,1});
        System.out.println("本机是否可达：" + local.isReachable(5000));
        // 获取该InetAddress实例对应的全限定域名
        System.out.println(local.getCanonicalHostName());
    }

    /**
     * 当URL地址里包含非西欧字符的字符串时，系统会将这些非西欧字符串转换成如图17.3所示的特殊字符串。
     * 编程过程中可能涉及普通字符串和这种特殊字符串的相关转换，这就需要使用URLDecoder和URLEncoder类。
     * ➢ URLDecoder类包含一个decode(String s, String enc)静态方法，它可以将看上去是乱码的特殊字符串转换成普通字符串。
     * ➢ URLEncoder类包含一个encode(String s, String enc)静态方法，它可以将普通字符串转换成
     *   application/x-www-form-urlencoded MIME字符串。
     */
    @Test
    public void urlCoder() throws UnsupportedEncodingException {
        // 将application/x-www-form-urlencoded字符串
        // 转换成普通字符串
        // 其中的字符串直接从图17.3所示窗口复制过来
        String keyWord = URLDecoder.decode("%B7%E8%BF%F1java", "GBK");
        System.out.println(keyWord);
        // 将普通字符串转换成
        // application/x-www-form-urlencoded字符串
        String urlStr = URLEncoder.encode("疯狂Android讲义" , "GBK");
        System.out.println(urlStr);
    }

    /**
     * URL（Uniform Resource Locator）对象代表统一资源定位器，它是指向互联网“资源”的指针。
     * 资源可以是简单的文件或目录，也可以是对更为复杂对象的引用，例如对数据库或搜索引擎的查询。
     * 在通常情况下，URL可以由协议名、主机、端口和资源组成，即满足如下格式：
     * protocol://host:port/resourceName
     *
     * JDK中还提供了一个URI（Uniform Resource Identifiers）类，其实例代表一个统一资源标识符，Java的URI不能用于定位任何资源，
     * 它的唯一作用就是解析。与此对应的是，URL则包含一个可打开到达该资源的输入流，可以将URL理解成URI的特例。
     *
     * URL类提供了多个构造器用于创建URL对象，一旦获得了URL对象之后，就可以调用如下方法来访问该URL对应的资源。
     * ➢ String getFile()：获取该URL的资源名。
     * ➢ String getHost()：获取该URL的主机名。
     * ➢ String getPath()：获取该URL的路径部分。
     * ➢ int getPort()：获取该URL的端口号。
     * ➢ String getProtocol()：获取该URL的协议名称。
     * ➢ String getQuery()：获取该URL的查询字符串部分。
     * ➢ URLConnection openConnection()：返回一个URLConnection对象，它代表了与URL所引用的远程对象的连接。
     * ➢ InputStream openStream()：打开与此URL的连接，并返回一个用于读取该URL资源的InputStream。
     * TODO
     */
    @Test
    public void urlTest() throws Exception {

    }
}
