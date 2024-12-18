package com.uptoser.java.javase.other.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.*;
import java.util.SortedMap;

/**
 * 新IO采用内存映射文件的方式来处理输入/输出，新IO将文件或文件的一段区域映射到内存中，这样就可以像访问内存一样来访问文件了
 * Java中与新IO相关的包如下。
 * ➢ java.nio包：主要包含各种与Buffer相关的类。
 * ➢ java.nio.channels包：主要包含与Channel和Selector相关的类。
 * ➢ java.nio.charset包：主要包含与字符集相关的类。
 * ➢ java.nio.channels.spi包：主要包含与Channel相关的服务提供者编程接口。
 * ➢ java.nio.charset.spi包：包含与字符集相关的服务提供者编程接口。
 *
 * Channel是对传统的输入/输出系统的模拟，在新IO系统中所有的数据都需要通过通道传输；
 * Channel与传统的InputStream、OutputStream最大的区别在于它提供了一个map()方法，
 * 通过该map()方法可以直接将“一块数据”映射到内存中
 * Buffer可以被理解成一个容器，它的本质是一个数组，发送到Channel中的所有对象都必须首先放到Buffer中，
 * 而从Channel中读取的数据也必须先放到Buffer中
 */
public class NIOMain {
    /**
     * Buffer类都没有提供构造器，通过使用如下方法来得到一个Buffer对象。
     * ➢ static XxxBuffer allocate(int capacity)：创建一个容量为capacity的XxxBuffer对象。
     * 但实际使用较多的是ByteBuffer和CharBuffer，其他Buffer子类则较少用到。其中ByteBuffer类还有一个子类：MappedByteBuffer，
     * 它用于表示Channel将磁盘文件的部分或全部内容映射到内存中后得到的结果，通常MappedByteBuffer对象由Channel的map()方法返回。
     *
     * 在Buffer中有三个重要的概念：容量（capacity）、界限（limit）和位置（position）。
     * ➢ 容量（capacity）：缓冲区的容量（capacity）表示该Buffer的最大数据容量，即最多可以存储多少数据。
     * 缓冲区的容量不可能为负值，创建后不能改变。
     * ➢ 界限（limit）：第一个不应该被读出或者写入的缓冲区位置索引。也就是说，位于limit后的数据既不可被读，也不可被写。
     * ➢ 位置（position）：用于指明下一个可以被读出的或者写入的缓冲区位置索引（类似于IO流中的记录指针）。
     * 当使用Buffer从Channel中读取数据时，position的值恰好等于已经读到了多少数据。
     * 当刚刚新建一个Buffer对象时，其position为0；如果从Channel中读取了2个数据到该Buffer中，
     * 则position为2，指向Buffer中第3个（第1个位置的索引为0）位置。
     *
     * Buffer中包含两个重要的方法，
     * 即flip()和clear()，flip()为从Buffer中取出数据做好准备，而clear()为再次向Buffer中装入数据做好准备。
     * 除此之外，Buffer还包含如下一些常用的方法。
     * ➢ int capacity()：返回Buffer的capacity大小。
     * ➢ boolean hasRemaining()：判断当前位置（position）和界限（limit）之间是否还有元素可供处理。
     * ➢ int limit()：返回Buffer的界限（limit）的位置。
     * ➢ Buffer limit(int newLt)：重新设置界限（limit）的值，并返回一个具有新的limit的缓冲区对象。
     * ➢ Buffer mark()：设置Buffer的mark位置，它只能在0和位置（position）之间做mark。
     * ➢ int position()：返回Buffer中的position值。
     * ➢ Buffer position(int newPs)：设置Buffer的position，并返回position被修改后的Buffer对象。
     * ➢ int remaining()：返回当前位置和界限（limit）之间的元素个数。
     * ➢ Buffer reset()：将位置（position）转到mark所在的位置。
     * ➢ Buffer rewind()：将位置（position）设置成0，取消设置的mark。
     * 除这些移动position、limit、mark的方法之外，Buffer的所有子类还提供了两个重要的方法：put()和get()方法，
     * 用于向Buffer中放入数据和从Buffer中取出数据。当使用put()和get()方法放入、取出数据时，Buffer既支持对单个数据的访问，
     * 也支持对批量数据的访问（以数组作为参数）。
     * 当使用put()和get()来访问Buffer中的数据时，分为相对和绝对两种。
     * ➢ 相对（Relative）：从Buffer的当前position处开始读取或写入数据，然后将位置（position）的值按处理元素的个数增加。
     * ➢ 绝对（Absolute）：直接根据索引向Buffer中读取或写入数据，使用绝对方式访问Buffer里的数据时，并不会影响位置（position）的值。
     *
     */
    public void bufferTest(){
        // 创建Buffer
        CharBuffer buff = CharBuffer.allocate(8);    //①
        System.out.println("capacity: "	+ buff.capacity());
        System.out.println("limit: " + buff.limit());
        System.out.println("position: " + buff.position());
        // 放入元素
        buff.put('a');
        buff.put('b');
        buff.put('c');      //②
        System.out.println("加入三个元素后，position = " + buff.position());
        // 调用flip()方法
        buff.flip();	  //③
        System.out.println("执行flip()后，limit = " + buff.limit());
        System.out.println("position = " + buff.position());
        // 取出第一个元素
        System.out.println("第一个元素(position=0)：" + buff.get());  // ④
        System.out.println("取出一个元素后，position = " + buff.position());
        // 调用clear方法
        buff.clear();     //⑤
        System.out.println("执行clear()后，limit = " + buff.limit());
        System.out.println("执行clear()后，position = " + buff.position());
        System.out.println("执行clear()后，缓冲区内容并没有被清除：" + "第三个元素为：" +  buff.get(2));    // ⑥
        System.out.println("执行绝对读取后，position = " + buff.position());

        /**
         * 通过allocate()方法创建的Buffer对象是普通Buffer，ByteBuffer还提供了一个allocateDirect()方法来创建直接Buffer。
         * 直接Buffer的创建成本比普通Buffer的创建成本高，但直接Buffer的读取效率更高。
         * 所以直接Buffer只适用于长生存期的Buffer，而不适用于短生存期、一次用完就丢弃的Buffer
         */
    }

    /**
     * Channel类似于传统的流对象，但与传统的流对象有两个主要区别。
     * ➢ Channel可以直接将指定文件的部分或全部直接映射成Buffer。
     * ➢ 程序不能直接访问Channel中的数据，包括读取、写入都不行，Channel只能与Buffer进行交互。
     *
     * Java为Channel接口提供了DatagramChannel、FileChannel、Pipe.SinkChannel、
     *      Pipe.SourceChannel、SelectableChannel、ServerSocketChannel、SocketChannel等实现类
     *
     * 所有的Channel都不应该通过构造器来直接创建，
     * 而是通过传统的节点InputStream、OutputStream的getChannel()方法来返回对应的Channel，
     * 不同的节点流获得的Channel不一样。
     * 例如，FileInputStream、FileOutputStream的getChannel()返回的是FileChannel
     */
    public void fileChannelTest() throws FileNotFoundException {
        final String CLASS_PATH = this.getClass().getClassLoader().getResource("").getPath();
        File f = new File(CLASS_PATH+"read.txt");
        readFile(f);
        try(
                // 创建FileInputStream，以该文件输入流创建FileChannel
                FileChannel inChannel = new FileInputStream(f).getChannel();
                // 以文件输出流创建FileBuffer，用以控制输出
                FileChannel outChannel = new FileOutputStream(CLASS_PATH+"out.txt").getChannel())
        {
            // 将FileChannel里的全部数据映射成ByteBuffer
             /*
            map()方法的方法签名为：MappedByteBuffer map（FileChannel.MapMode mode，long position，long size），
            第一个参数执行映射时的模式，分别有只读、读写等模式；
            而第二个、第三个参数用于控制将Channel的哪些数据映射成ByteBuffer。
             */
            MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY , 0 , f.length());   // ①
            // 使用字符集来创建解码器
            Charset charset = Charset.forName("UTF-8");
            // 直接将buffer里的数据全部输出
            outChannel.write(buffer);     // ②
            // 再次调用buffer的clear()方法，复原limit、position的位置
            buffer.clear();
            // 创建解码器(CharsetDecoder)对象
            CharsetDecoder decoder = charset.newDecoder();
            // 使用解码器将ByteBuffer转换成CharBuffer
            CharBuffer charBuffer =  decoder.decode(buffer);
            // CharBuffer的toString方法可以获取对应的字符串
            System.out.println(charBuffer);
            /**
             * 虽然FileChannel既可读取也可写入，但FileInputStream获取的Channel只能读，而FileOutputStream获取的Channel只能写
             * RandomAccessFile返回的FileChannel()是只读的还是读写的，则取决于RandomAccessFile打开文件的模式
             */
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * 通过多次循环读取数据
     */
    private void readFile(File f) throws FileNotFoundException {
        try(
                // 创建文件输入流
                FileInputStream fis = new FileInputStream(f);
                // 创建一个FileChannel
                FileChannel fcin = fis.getChannel())
        {
            // 定义一个ByteBuffer对象，用于重复取水
            ByteBuffer bbuff = ByteBuffer.allocate(123);
            // 将FileChannel中数据放入ByteBuffer中
            while( fcin.read(bbuff) != -1 )
            {
                // 锁定Buffer的空白区
                bbuff.flip();
                // 创建Charset对象
                Charset charset = StandardCharsets.UTF_8;
                // 创建解码器(CharsetDecoder)对象
                CharsetDecoder decoder = charset.newDecoder();
                /**忽略输入长度不正确的情况*/
                decoder.onMalformedInput(CodingErrorAction.IGNORE);
                // 将ByteBuffer的内容转码
                CharBuffer cbuff = decoder.decode(bbuff);
                System.out.print(cbuff);
                // 将Buffer初始化，为下一次读取数据做准备
                bbuff.clear();
            }
            System.out.println("\n-------------------------------");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void charsetTest() throws CharacterCodingException {
        // 获取Java支持的全部字符集
        SortedMap<String,Charset> map = Charset.availableCharsets();
//        for (String alias : map.keySet())
//        {
//            // 输出字符集的别名和对应的Charset对象
//            System.out.println(alias + "----->"
//                    + map.get(alias));
//        }
        /**
         * Java 7新增了一个StandardCharsets类，
         * 该类里包含了ISO_8859_1、UTF_8、UTF_16等类变量，这些类变量代表了最常用的字符集对应的Charset对象。
         */
        Charset utf8 = StandardCharsets.UTF_8;
        // 创建简体中文对应的Charset
        Charset gbk = Charset.forName("GBK");
        /*
        获得了Charset对象之后，就可以通过该对象的newDecoder()、newEncoder()
        这两个方法分别返回CharsetDecoder和CharsetEncoder对象，代表该Charset的解码器和编码器
         */
        // 获取cn对象对应的编码器和解码器
        CharsetEncoder cnEncoder = gbk.newEncoder();
        CharsetDecoder cnDecoder = gbk.newDecoder();
        // 创建一个CharBuffer对象
        CharBuffer cbuff = CharBuffer.allocate(8);
        cbuff.put('孙');
        cbuff.put('悟');
        cbuff.put('空');
        cbuff.flip();
        // 将CharBuffer中的字符序列转换成字节序列
        ByteBuffer bbuff = cnEncoder.encode(cbuff);
        // 循环访问ByteBuffer中的每个字节
        for (int i = 0; i < bbuff.capacity() ; i++)
        {
            System.out.print(bbuff.get(i) + " ");
        }
        // 将ByteBuffer的数据解码成字符序列
        System.out.println("\n" + cnDecoder.decode(bbuff));
        /**
         * 上面程序中的两行粗体字代码分别实现了将CharBuffer转换成ByteBuffer，将ByteBuffer转换成CharBuffer的功能。
         * 实际上，Charset类也提供了如下三个方法。
         * ➢ CharBuffer decode(ByteBuffer bb)：将ByteBuffer中的字节序列转换成字符序列的便捷方法。
         * ➢ ByteBuffer encode(CharBuffer cb)：将CharBuffer中的字符序列转换成字节序列的便捷方法。
         * ➢ ByteBuffer encode(String str)：将String中的字符序列转换成字节序列的便捷方法。
         */
    }
    public static void main(String[] args) throws FileNotFoundException, CharacterCodingException {
        NIOMain a = new NIOMain();
        a.bufferTest();
        System.out.println("------------------------------");
        a.fileChannelTest();
        System.out.println("------------------------------");
        a.charsetTest();
        /**
         * 在NIO中，Java提供了FileLock来支持文件锁定功能，
         * 在FileChannel中提供的lock()/tryLock()方法可以获得文件锁FileLock对象，从而锁定文件。
         * lock()和tryLock()方法存在区别：当lock()试图锁定某个文件时，如果无法得到文件锁，程序将一直阻塞；
         * 而tryLock()是尝试锁定文件，它将直接返回而不是阻塞，如果获得了文件锁，该方法则返回该文件锁，否则将返回null。
         * 如果FileChannel只想锁定文件的部分内容，而不是锁定全部内容，则可以使用如下的lock()或tryLock()方法。
         * ➢ lock(long position, long size, boolean shared)：对文件从position开始，长度为size的内容加锁，该方法是阻塞式的。
         * ➢ tryLock(long position, long size, boolean shared)：非阻塞式的加锁方法。参数的作用与上一个方法类似。
         */
//        try(
//                // 使用FileOutputStream获取FileChannel
//                FileChannel channel = new FileOutputStream("a.txt")
//                        .getChannel())
//        {
//            // 使用非阻塞式方式对指定文件加锁
//            FileLock lock = channel.tryLock();
//            // 程序暂停10s
//            Thread.sleep(10000);
//            // 释放锁
//            lock.release();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

    }
}
