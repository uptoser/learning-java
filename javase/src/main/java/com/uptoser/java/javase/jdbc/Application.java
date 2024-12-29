package com.uptoser.java.javase.jdbc;

/**
 * JDBC的全称是Java Database Connectivity，即Java数据库连接，它是一种可以执行SQL语句的Java API
 * 数据库驱动程序是JDBC程序和数据库之间的转换层，数据库驱动程序负责将JDBC调用映射成特定的数据库调用
 * JDBC驱动通常有如下4种类型。
 * ➢ 第1种JDBC驱动：称为JDBC-ODBC桥，这种驱动是最早实现的JDBC驱动程序，主要目的是为了快速推广JDBC。这种驱动将JDBC API映射到ODBC API。这种方式在Java 8中已经被删除了。
 * ➢ 第2种JDBC驱动：直接将JDBC API映射成数据库特定的客户端API。这种驱动包含特定数据库的本地代码，用于访问特定数据库的客户端。
 * ➢ 第3种JDBC驱动：支持三层结构的JDBC访问方式，主要用于Applet阶段，通过Applet访问数据库。
 * ➢ 第4种JDBC驱动：是纯Java的，直接与数据库实例交互。这种驱动是智能的，它知道数据库使用的底层协议。这种驱动是目前最流行的JDBC驱动。
 *
 * 还有一种名为ODBC的技术，其全称是Open Database Connectivity，即开放数据库连接。ODBC和JDBC很像，严格来说，应该是JDBC模仿了ODBC的设计。
 * ODBC也允许应用程序通过一组通用的API访问不同的数据库管理系统，从而使得基于ODBC的应用程序可以在不同的数据库之间切换。
 * 同样，ODBC也需要各数据库厂商提供相应的驱动程序，而ODBC则负责管理这些驱动程序。
 *
 * 相对于ODBC而言，JDBC更加简单。总结起来，JDBC比ODBC多了如下几个优势。
 * ➢ ODBC更复杂，ODBC中有几个命令需要配置很多复杂的选项，而JDBC则采用简单、直观的方式来管理数据库连接。
 * ➢ JDBC比ODBC安全性更高，更易部署。
 *
 * 标准的SQL语句通常可分为如下几种类型。
 * ➢ 查询语句：主要由select关键字完成，查询语句是SQL语句中最复杂、功能最丰富的语句。
 * ➢ DML（Data Manipulation Language，数据操作语言）语句：主要由insert、update和delete三个关键字完成。
 * ➢ DDL（Data Definition Language，数据定义语言）语句：主要由create、alter、drop和truncate四个关键字完成。
 * ➢ DCL（Data Control Language，数据控制语言）语句：主要由grant和revoke两个关键字完成。
 * ➢ 事务控制语句：主要由commit、rollback和savepoint三个关键字完成。
 */
public class Application {
}
