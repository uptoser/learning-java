package com.uptoser.java.javase.other.io;

/**
 * RandomAccessFile是Java输入/输出流体系中功能最丰富的文件内容访问类，
 * 它提供了众多的方法来访问文件内容，它既可以读取文件内容，也可以向文件输出数据
 * RandomAccessFile支持“随机访问”的方式，程序可以直接跳转到文件的任意地方来读写数据
 * RandomAccessFile的方法虽然多，但它有一个最大的局限，就是只能读写文件，不能读写其他IO节点。
 *
 * RandomAccessFile对象也包含了一个记录指针，用以标识当前读写处的位置
 * RandomAccessFile包含了如下两个方法来操作文件记录指针。
 * ➢ long getFilePointer()：返回文件记录指针的当前位置。
 * ➢ void seek(long pos)：将文件记录指针定位到pos位置。
 *
 * RandomAccessFile类有两个构造器，其实这两个构造器基本相同，只是指定文件的形式不同而已—一个使用String参数来指定文件名，
 * 一个使用File参数来指定文件本身。除此之外，创建RandomAccessFile对象时还需要指定一个mode参数，
 * 该参数指定RandomAccessFile的访问模式，该参数有如下4个值。
 * ➢ "r"：以只读方式打开指定文件。如果试图对该RandomAccessFile执行写入方法，都将抛出IOException异常。
 * ➢ "rw"：以读、写方式打开指定文件。如果该文件尚不存在，则尝试创建该文件。
 * ➢ "rws"：以读、写方式打开指定文件。相对于"rw"模式，还要求对文件的内容或元数据的每个更新都同步写入到底层存储设备。
 * ➢ "rwd"：以读、写方式打开指定文件。相对于"rw"模式，还要求对文件内容的每个更新都同步写入到底层存储设备。
 *
 * RandomAccessFile依然不能向文件的指定位置插入内容，如果直接将文件记录指针移动到中间某位置后开始输出，
 * 则新输出的内容会覆盖文件中原有的内容。如果需要向指定位置插入内容，程序需要先把插入点后面的内容读入缓冲区，
 * 等把需要插入的数据写入文件后，再将缓冲区的内容追加到文件后面。
 */
public class RandomAccessFileMain {
    /*
    多线程断点的网络下载工具（如FlashGet等）就可通过RandomAccessFile类来实现，
    所有的下载工具在下载开始时都会建立两个文件：一个是与被下载文件大小相同的空文件，
    一个是记录文件指针的位置文件，下载工具用多条线程启动输入流来读取网络数据，
    并使用RandomAccessFile将从网络上读取的数据写入前面建立的空文件中，每写一些数据后，
    记录文件指针的文件就分别记下每个RandomAccessFile当前的文件指针位置——网络断开后，
    再次开始下载时，每个RandomAccessFile都根据记录文件指针的文件中记录的位置继续向下写数据
     */
}
