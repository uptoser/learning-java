package com.uptoser.java.javase.other.reflect;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.util.Enumeration;
import java.util.Properties;

/**
 * 类加载器负责加载所有的类，系统为所有被载入内存中的类生成一个java.lang.Class实例。一旦一个类被载入JVM中，同一个类就不会被再次载入了
 * 在Java中，一个类用其全限定类名（包括包名和类名）作为标识；但在JVM中，一个类用其全限定类名和其类加载器作为唯一标识
 *      例如，如果在pg的包中有一个名为Person的类，被类加载器ClassLoader的实例kl负责加载，
 *      则该Person类对应的Class对象在JVM中表示为（Person、pg、kl）。
 *      这意味着两个类加载器加载的同名类：（Person、pg、kl）和（Person、pg、kl2）是不同的，
 *      它们所加载的类也是完全不同、互不兼容的。
 * 当JVM启动时，会形成由三个类加载器组成的初始类加载器层次结构。
 * ➢ Bootstrap ClassLoader：根类加载器。也被称为引导（也称为原始或根）类加载器，它负责加载Java的核心类
 * ➢ Extension ClassLoader：扩展类加载器。
 * ➢ System ClassLoader：系统类加载器。
 * 除了可以使用Java提供的类加载器之外，开发者也可以实现自己的类加载器，自定义的类加载器通过继承ClassLoader来实现
 *
 * 系统类加载器是AppClassLoader的实例，扩展类加载器PlatformClassLoader的实例。实际上，这两个类都是URLClassLoader类的实例。
 */
public class ClassLoaderTest {

    /**
     * 类加载器加载Class大致要经过如下8个步骤。
     * ① 检测此Class是否载入过（即在缓存区中是否有此Class），如果有则直接进入第8步，否则接着执行第2步。
     * ② 如果父类加载器不存在（如果没有父类加载器，则要么parent一定是根类加载器，要么本身就是根类加载器），则跳到第4步执行；如果父类加载器存在，则接着执行第3步。
     * ③ 请求使用父类加载器去载入目标类，如果成功载入则跳到第8步，否则接着执行第5步。
     * ④ 请求使用根类加载器来载入目标类，如果成功载入则跳到第8步，否则跳到第7步。
     * ⑤ 当前类加载器尝试寻找Class文件（从与此ClassLoader相关的类路径中寻找），如果找到则执行第6步，如果找不到则跳到第7步。
     * ⑥ 从文件中载入Class，成功载入后跳到第8步。
     * ⑦ 抛出ClassNotFoundException异常。
     * ⑧ 返回对应的java.lang.Class对象。
     * 其中，第5、6步允许重写ClassLoader的findClass()方法来实现自己的载入策略，甚至重写loadClass()方法来实现自己的载入过程。
     */
    public void systemClassLoader() throws IOException {
        // 获取系统类加载器
        ClassLoader systemLoader = ClassLoader.getSystemClassLoader();
        System.out.println("系统类加载器：" + systemLoader);
		/*
		获取系统类加载器的加载路径——通常由CLASSPATH环境变量指定
		如果操作系统没有指定CLASSPATH环境变量，默认以当前路径作为
		系统类加载器的加载路径
		*/
        Enumeration<URL> em1 = systemLoader.getResources("");
        while(em1.hasMoreElements())
        {
            System.out.println(em1.nextElement());
        }
        // 获取系统类加载器的父类加载器：得到扩展类加载器
        ClassLoader extensionLader = systemLoader.getParent();
        System.out.println("扩展类加载器：" + extensionLader);
        System.out.println("扩展类加载器的加载路径："
                + System.getProperty("java.ext.dirs"));
        System.out.println("扩展类加载器的parent: "
                + extensionLader.getParent());
    }

    /**
     * URLClassLoader功能比较强大，它既可以从本地文件系统获取二进制文件来加载类，也可以从远程主机获取二进制文件来加载类。
     * URLClassLoader类提供了如下两个构造器。
     * ➢ URLClassLoader(URL[] urls)：使用默认的父类加载器创建一个ClassLoader对象，该对象将从urls所指定的系列路径来查询并加载类。
     * ➢ URLClassLoader(URL[] urls, ClassLoader parent)：使用指定的父类加载器创建一个ClassLoader对象，其他功能与前一个构造器相同。
     */
    public void urlClassLoader() throws Exception {
         /*
        下面程序示范了如何直接从文件系统中加载MySQL驱动，并使用该驱动来获取数据库连接。
        通过这种方式来获取数据库连接，可以无须将MySQL驱动添加到CLASSPATH环境变量中
         */
        System.out.println(getConn("jdbc:mysql://localhost:3306/mysql" , "root" , "32147"));
    }

    private static Connection conn;
    //定义一个获取数据库连接方法
    public static Connection getConn(String url , String user , String pass) throws Exception{
        if (conn == null){
            // 创建一个URL数组
            URL[] urls = {new URL("file:mysql-connector-java-3.1.10-bin.jar")};
            // 以默认的ClassLoader作为父ClassLoader，创建URLClassLoader
            URLClassLoader myClassLoader = new URLClassLoader(urls);
            // 加载MySQL的JDBC驱动，并创建默认实例
            Driver driver = (Driver)myClassLoader.
                    loadClass("com.mysql.jdbc.Driver").newInstance();
            // 创建一个设置JDBC连接属性的Properties对象
            Properties props = new Properties();
            // 至少需要为该对象传入user和password两个属性
            props.setProperty("user" , user);
            props.setProperty("password" , pass);
            // 调用Driver对象的connect方法来取得数据库连接
            conn = driver.connect(url , props);
        }
        return conn;
    }

    public static void main(String[] args) throws IOException {
        ClassLoaderTest test = new ClassLoaderTest();
        test.systemClassLoader();
    }
}
