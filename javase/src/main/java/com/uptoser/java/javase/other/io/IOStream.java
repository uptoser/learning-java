package com.uptoser.java.javase.other.io;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 划分输入/输出流时是从程序运行所在内存的角度来考虑的(流向内存为输入流)
 * 输入流主要由InputStream和Reader作为基类
 * 输出流主要由OutputStream和Writer作为基类
 */
public class IOStream {
    //ClassPath路径
    public static final String CLASS_PATH = ClassLoader.getSystemResource("").getFile();

    /**
     * OutputStream和Writer也非常相似，两个流都提供了如下三个方法。
     * ➢ void write(int c)：将指定的字节/字符输出到输出流中，其中c既可以代表字节，也可以代表字符。
     * ➢ void write(byte[]/char[] buf)：将字节数组/字符数组中的数据输出到指定输出流中。
     * ➢ void write(byte[]/char[] buf, int off, int len)：将字节数组/字符数组中从off位置开始，长度为len的字节/字符输出到输出流中。
     * 因为字符流直接以字符作为操作单位，所以Writer可以用字符串来代替字符数组，即以String对象作为参数。
     *
     * Writer里还包含如下两个方法。
     * ➢ void write(String str)：将str字符串里包含的字符输出到指定输出流中。
     * ➢ void write(String str, int off, int len)：将str字符串里从off位置开始，长度为len的字符输出到指定输出流中。
     */
    void output(){
        System.out.println("--------------FileInputStream and FileOutputStream--------------");
        try(
            // 创建字节输入流
            FileInputStream fis = new FileInputStream(CLASS_PATH+"read.txt");
            // 创建字节输出流
            FileOutputStream fos = new FileOutputStream(CLASS_PATH+"newRead.txt"))
        {
            byte[] bbuf = new byte[32];
            int hasRead = 0;
            // 循环从输入流中取出数据
            while ((hasRead = fis.read(bbuf)) > 0 ){
                // 每读取一次，即写入文件输出流，读了多少，就写多少。
                fos.write(bbuf , 0 , hasRead);
            }
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
        System.out.println("-------------FileWriter---------------");
        try(
                FileWriter fw = new FileWriter(CLASS_PATH+"poem.txt"))
        {
            fw.write("锦瑟 - 李商隐\r\n");
            fw.write("锦瑟无端五十弦，一弦一柱思华年。\r\n");
            fw.write("庄生晓梦迷蝴蝶，望帝春心托杜鹃。\r\n");
            fw.write("沧海月明珠有泪，蓝田日暖玉生烟。\r\n");
            fw.write("此情可待成追忆，只是当时已惘然。\r\n");
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        input();
    }
    /**
     * 在InputStream里包含如下三个方法。(字节流)
     * ➢ int read()：从输入流中读取单个字节，返回所读取的字节数据（字节数据可直接转换为int类型）。
     * ➢ int read(byte[] b)：从输入流中最多读取b.length个字节的数据，并将其存储在字节数组b中，返回实际读取的字节数。
     * ➢ int read(byte[] b, int off, int len)：从输入流中最多读取len个字节的数据，并将其存储在数组b中，放入数组b中时，
     * 并不是从数组起点开始，而是从off位置开始，返回实际读取的字节数。
     *
     * 在Reader里包含如下三个方法。(字符流)
     * ➢ int read()：从输入流中读取单个字符，返回所读取的字符数据（字符数据可直接转换为int类型）。
     * ➢ int read(char[] c)：从输入流中最多读取c.length个字符的数据，并将其存储在字符数组c中，返回实际读取的字符数。
     * ➢ int read(char[] c, int off, int len)：从输入流中最多读取len个字符的数据，并将其存储在字符数组c中，放入数组c中时，
     * 并不是从数组起点开始，而是从off位置开始，返回实际读取的字符数。
     */
    void input(){
        System.out.println("-------------FileReader---------------");
        try(
                // 创建字符输入流
                FileReader fr = new FileReader(CLASS_PATH +"poem.txt"))
        {
            // 创建一个长度为32的“竹筒”
            char[] cbuf = new char[32];
            // 用于保存实际读取的字符数
            int hasRead = 0;
            // 使用循环来重复“取水”过程
            while ((hasRead = fr.read(cbuf)) > 0 )
            {
                // 取出“竹筒”中水滴（字符），将字符数组转换成字符串输入！
                System.out.print(new String(cbuf , 0 , hasRead));
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * 使用处理流时的典型思路是，使用处理流来包装节点流，程序通过处理流来执行输入/输出功能
     * 实际识别处理流非常简单，只要流的构造器参数不是一个物理节点，而是已经存在的流，那么这种流就一定是处理流
     * 关于使用处理流的优势，归纳起来就是两点：
     * ①对开发人员来说，使用处理流进行输入/输出操作更简单；
     * ②使用处理流的执行效率更高。
     */
    void processStream(){
        //使用PrintStream处理流来包装OutputStream
        try(
            FileOutputStream fos = new FileOutputStream(CLASS_PATH+"test.txt");
            PrintStream ps = new PrintStream(fos)
        ){
            // 使用PrintStream执行输出
            ps.println("普通字符串");
            // 直接使用IOStream输出对象
            ps.println(new IOStream());
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }finally {
            System.out.println("----------The 'processStream()' has been executed...--------");
        }
    }

    /**
     * 以数组为物理节点的节点流，字节流以字节数组为节点，字符流以字符数组为节点
     * 使用字符串作为物理节点的字符输入/输出流的用法
     */
    public String stringNodeSteam(){
        System.out.println("---------StringReader--------------");
        String src = "从明天起，做一个幸福的人\n"
                + "喂马，劈柴，周游世界\n"
                + "从明天起，关心粮食和蔬菜\n"
                + "我有一所房子，面朝大海，春暖花开\n"
                + "从明天起，和每一个亲人通信\n"
                + "告诉他们我的幸福\n";
        char[] buffer = new char[32];
        int hasRead = 0;
        try(
                StringReader sr = new StringReader(src))
        {
            // 采用循环读取的访问读取字符串
            while((hasRead = sr.read(buffer)) > 0)
            {
                System.out.print(new String(buffer ,0 , hasRead));
            }
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        try(
                // 创建StringWriter时，实际上以一个StringBuffer作为输出节点
                // 下面指定的20就是StringBuffer的初始长度
                StringWriter sw = new StringWriter())
        {
            // 调用StringWriter的方法执行输出
            sw.write("有一个美丽的新世界，\n");
            sw.write("她在远方等我,\n");
            sw.write("哪里有天真的孩子，\n");
            sw.write("还有姑娘的酒窝\n");
            System.out.println("---------------StringWriter-----------");
            // 使用toString()方法返回StringWriter的字符串节点的内容
            System.out.println(src = sw.toString());
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return src;
    }
    /**
     * 转换流
     * 输入/输出流体系中还提供了两个转换流
     * InputStreamReader将字节输入流转换成字符输入流
     * OutputStreamWriter将字节输出流转换成字符输出流
     */
    public void transformStream(){
        System.out.println("-------------InputStreamReader and BufferedReader----------");
        try(
            FileInputStream fis = new FileInputStream(CLASS_PATH+"read.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bf = new BufferedReader(isr)
        ){
            String line;
            while ((line = bf.readLine()) != null){
                System.out.println(line);
            }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

    }

    /**
     * 推回输入流
     *
     * 在输入/输出流体系中，有两个特殊的流与众不同，就是PushbackInputStream和PushbackReader，它们都提供了如下三个方法。
     * ➢ void unread(byte[]/char[] buf)：将一个字节/字符数组内容推回到推回缓冲区里，从而允许重复读取刚刚读取的内容。
     * ➢ void unread(byte[]/char[] b, int off, int len)：将一个字节/字符数组里从off开始，长度为len字节/字符的内容推回到推回缓冲区里
     * ➢ void unread(int b)：将一个字节/字符推回到推回缓冲区里，从而允许重复读取刚刚读取的内容。
     *
     * 这两个推回输入流都带有一个推回缓冲区，当程序调用这两个推回输入流的unread()方法时，
     * 系统将会把指定数组的内容推回到该缓冲区里，而推回输入流每次调用read()方法时总是先从推回缓冲区读取，
     * 只有完全读取了推回缓冲区的内容后，但还没有装满read()所需的数组时才会从原输入流中读取
     *
     * 当程序创建一个PushbackInputStream和PushbackReader时需要指定推回缓冲区的大小，默认的推回缓冲区的长度为1。
     * 如果程序中推回到推回缓冲区的内容超出了推回缓冲区的大小，将会引发Pushback buffer overflow的IOException异常
     */
    public void pushbackInputStream(){
        System.out.println("------pushbackInputStream----------");
        try(
                // 创建一个PushbackReader对象，指定推回缓冲区的长度为64
                PushbackReader pr = new PushbackReader(new FileReader(CLASS_PATH + "read.txt"),1)
        ){
            char[] c = new char[1];
            while (pr.read(c) != -1){
                System.out.print(c[0]);
                // 查看是否包含目标字符串, 如果包含目标字符串
                if (c[0]=='马'){
                    // 推回缓冲区
                    pr.unread(c[0]);
                    // 再次读取指定长度的内容（就是目标字符串之前的内容）
                    pr.read(c);
                    // 打印读取的内容
                    System.out.print(c[0]);
                }
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

    }

    public static void main(String[] args) {
        IOStream io = new IOStream();
        io.output();
        io.processStream();
        io.stringNodeSteam();
        io.transformStream();
        io.pushbackInputStream();

        /**
         * 输入/输出流和数组的相互转换
         */
        if(true){return;}

        byte[] src = "abc,def,ghi".getBytes(StandardCharsets.ISO_8859_1);
        System.out.println("-----------数组转输入-----------");
        try(
                //数组转输入
                ByteArrayInputStream bis = new ByteArrayInputStream(src);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ){
            System.out.println("-----------输入转输出-----------");
            byte[] bytes = new byte[5];
            int length = -1;
            while ((length = bis.read(bytes)) > 0){
                bos.write(bytes,0,length);
            }
            System.out.println("-----------输出转数组-----------");
            /*
            还可以通过Spring的FileCopyUtils和Apache Commons IO库的IOUtils将输入流转换为字节数组
             */
            bytes = bos.toByteArray();
            System.out.println("输出："+new String(bytes,StandardCharsets.ISO_8859_1));

        }catch (IOException ioe){
            ioe.printStackTrace();
        }



    }
}
