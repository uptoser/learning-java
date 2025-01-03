package com.uptoser.java.javase.jdbc;

import java.sql.*;

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
    /**
     * Java支持JDBC 4.2标准，JDBC 4.2在原有JDBC标准上增加了一些新特性。下面介绍这些JDBC API时会提到Java 8新增的功能。
     * ➢ DriverManager：用于管理JDBC驱动的服务类。程序中使用该类的主要功能是获取Connection对象，该类包含如下方法。
     * • public static synchronized Connection getConnection(String url, String user, String pass) throws SQLException：该方法获得url对应数据库的连接。
     * ➢ Connection：代表数据库连接对象，每个Connection代表一个物理连接会话。要想访问数据库，必须先获得数据库连接。该接口的常用方法如下。
     * • Statement createStatement() throws SQLException：该方法返回一个Statement对象。
     * • PreparedStatement prepareStatement(String sql) throws SQLException：该方法返回预编译的Statement对象，即将SQL语句提交到数据库进行预编译。
     * • CallableStatement prepareCall(String sql) throws SQLException：该方法返回CallableStatement对象，该对象用于调用存储过程。
     * 上面三个方法都返回用于执行SQL语句的Statement对象，PreparedStatement、CallableStatement是Statement的子类，只有获得了Statement之后才可执行SQL语句。
     * 除此之外，Connection还有如下几个用于控制事务的方法。
     * ➢ Savepoint setSavepoint()：创建一个保存点。
     * ➢ Savepoint setSavepoint(String name)：以指定名字来创建一个保存点。
     * ➢ void setTransactionIsolation(int level)：设置事务的隔离级别。
     * ➢ void rollback()：回滚事务。
     * ➢ void rollback(Savepoint savepoint)：将事务回滚到指定的保存点。
     * ➢ void setAutoCommit(boolean autoCommit)：关闭自动提交，打开事务。
     * ➢ void commit()：提交事务。
     *
     * ➢ PreparedStatement：预编译的Statement对象。
     * PreparedStatement是Statement的子接口，它允许数据库预编译SQL语句（这些SQL语句通常带有参数），
     * 以后每次只改变SQL命令的参数，避免数据库每次都需要编译SQL语句，因此性能更好。相对于Statement而言，
     * 使用PreparedStatement执行SQL语句时，无须再传入SQL语句，只要为预编译的SQL语句传入参数值即可。
     * 所以它比Statement多了如下方法。
     * • void setXxx（int parameterIndex，Xxx value）：该方法根据传入参数值的类型不同，需要使用不同的方法。
     * 传入的值根据索引传给SQL语句中指定位置的参数。
     *
     * ➢ ResultSet：结果集对象。该对象包含访问查询结果的方法，ResultSet可以通过列索引或列名获得列数据。
     * 它包含了如下常用方法来移动记录指针。
     * • void close()：释放ResultSet对象。
     * • boolean absolute（int row）：将结果集的记录指针移动到第row行，如果row是负数，则移动到倒数第row行。
     * 如果移动后的记录指针指向一条有效记录，则该方法返回true。
     * • void beforeFirst()：将ResultSet的记录指针定位到首行之前，
     * 这是ResultSet结果集记录指针的初始状态—记录指针的起始位置位于第一行之前。
     * • boolean first()：将ResultSet的记录指针定位到首行。如果移动后的记录指针指向一条有效记录，则该方法返回true。
     * • boolean previous()：将ResultSet的记录指针定位到上一行。如果移动后的记录指针指向一条有效记录，则该方法返回true。
     * • boolean next()：将ResultSet的记录指针定位到下一行，如果移动后的记录指针指向一条有效记录，则该方法返回true。
     * • boolean last()：将ResultSet的记录指针定位到最后一行，如果移动后的记录指针指向一条有效记录，则该方法返回true。
     * • void afterLast()：将ResultSet的记录指针定位到最后一行之后。
     *
     * ➢ CallableStatement： 调用存储过程
     * 可以通过Connection的prepareCall()方法来创建CallableStatement对象，创建该对象时需要传入调用存储过程的SQL语句{call过程名(?, ?, ?...)}
     * 可以通过CallableStatement的setXxx()方法为传入参数设置值
     * CallableStatement需要调用registerOutParameter()方法来注册该参数
     *
     */
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try(
                // 2.使用DriverManager获取数据库连接,
                // 其中返回的Connection就代表了Java程序和数据库的连接
                // 不同数据库的URL写法需要查驱动文档知道，用户名、密码由DBA分配
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://192.168.3.200:3306/employees"
                        , "mysql" , "mysql");
                // 3.使用Connection来创建一个Statment对象
                Statement stmt = conn.createStatement();


        ){
            // 4.执行SQL语句
			/*
			Statement有三种执行sql语句的方法：
			1 execute 可执行任何SQL语句。- 返回一个boolean值，
			  如果执行后第一个结果是ResultSet，则返回true，否则返回false
			2 executeQuery 执行Select语句 － 返回查询到的结果集
			3 executeUpdate 用于执行DML语句。－ 返回一个整数，
			  代表被SQL语句影响的记录条数
			*/
            long l = stmt.executeLargeUpdate("update account set balance=balance+100 where id = '1'");
            long l2 = stmt.executeLargeUpdate("CREATE TABLE tmp_table (id int ,name varchar(5))");
            System.out.println("LONG："+l2);
            int i = stmt.executeUpdate("DROP TABLE tmp_table");
            System.out.println("INT:"+i);
            System.out.println("update语句返回的行数为："+l);

            ResultSet rs = stmt.executeQuery("select * from account");
            System.out.println("id" + "\t"+ "account" + "\t"+ "balance");
            /*
            ResultSetMetaData是用于分析结果集的元数据接口
            常用的方法有如下三个。
            ➢ int getColumnCount()：返回该ResultSet的列数量。
            ➢ String getColumnName(int column)：返回指定索引的列名。
            ➢ int getColumnType(int column)：返回指定索引的列类型。
             */
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            // 指向行、特定列的值，不断地使用next()将记录指针下移一行，
            // 如果移动之后记录指针依然指向有效行，则next()方法返回true。
            while(rs.next()){
                for (int j = 0; j < columnCount; j++) {
                    // ResultSet有系列的getXxx(列索引 | 列名)，用于获取记录指针
                    System.out.print(rs.getString(j+1)+"\t");
                }
                System.out.println();
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
