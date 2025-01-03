package com.uptoser.java.javase.jdbc;

/**
 * Blob（Binary Long Object）是二进制长对象的意思，Blob列通常用于存储大文件，
 * 典型的Blob内容是一张图片或一个声音文件，由于它们的特殊性，必须使用特殊的方式来存储
 *
 * 将Blob数据插入数据库需要使用PreparedStatement，该对象有一个方法：setBinaryStream（int parameterIndex，InputStream x）
 * 当需要从ResultSet里取出Blob数据时，可以调用ResultSet的getBlob（int columnIndex）方法，该方法将返回一个Blob对象，
 * Blob对象提供了getBinaryStream()方法来获取该Blob数据的输入流，
 * 也可以使用Blob对象提供的getBytes()方法直接取出该Blob对象封装的二进制数据
 *
 *
 *
 */
public class SetGetBinaryStreamMain {
}
