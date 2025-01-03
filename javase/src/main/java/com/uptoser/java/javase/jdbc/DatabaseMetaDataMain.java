package com.uptoser.java.javase.jdbc;

import java.io.FileInputStream;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/**
 * MySQL数据库使用information_schema数据库来保存系统表，在该数据库里包含了大量系统表，常用系统表的简单介绍如下。
 * ➢ tables：存放数据库里所有数据表的信息。
 * ➢ schemata：存放数据库里所有数据库（与MySQL的Schema对应）的信息。
 * ➢ views：存放数据库里所有视图的信息。
 * ➢ columns：存放数据库里所有列的信息。
 * ➢ triggers：存放数据库里所有触发器的信息。
 * ➢ routines：存放数据库里所有存储过程和函数的信息。
 * ➢ key_column_usage：存放数据库里所有具有约束的键信息。
 * ➢ table_constraints：存放数据库里全部约束的表信息。
 * ➢ statistics：存放数据库里全部索引的信息。
 */
public class DatabaseMetaDataMain {
    private final String url;
    private final String user;
    private final String pass;
    public DatabaseMetaDataMain() throws Exception {
        // 使用Properties类来加载属性文件
        Properties props = new Properties();
        URL resource = this.getClass().getClassLoader().getResource("mysql.ini");
        props.load(new FileInputStream(resource.getPath()));
        String driver = props.getProperty("driver");
        url = props.getProperty("url");
        user = props.getProperty("user");
        pass = props.getProperty("pass");
        // 加载驱动
        Class.forName(driver);
    }

    public void info() throws Exception {
        // 获取数据库连接
        Connection conn = DriverManager.getConnection(url, user, pass);
        // 获取的DatabaseMetaData对象
        DatabaseMetaData dbmd = conn.getMetaData();
        // 获取MySQL支持的所有表类型
        ResultSet rs = dbmd.getTableTypes();
        System.out.println("--MySQL支持的表类型信息--");
        printResultSet(rs);
        // 获取当前数据库的全部数据表
        rs = dbmd.getTables(null, null, "%", new String[]{"TABLE"});
        System.out.println("--当前数据库里的数据表信息--");
        printResultSet(rs);
        // 获取account表的主键
        rs = dbmd.getPrimaryKeys(null, null, "account");
        System.out.println("--account表的主键信息--");
        printResultSet(rs);
        // 获取当前数据库的全部存储过程
        rs = dbmd.getProcedures(null, null, "%");
        System.out.println("--当前数据库里的存储过程信息--");
        printResultSet(rs);
        // 获取a表和be之间的外键约束
        //rs = dbmd.getCrossReference(null, null, "a", null, null, "b");
        //System.out.println("--a表和b之间" + "的外键约束--");
        //printResultSet(rs);
        // 获取account表的全部数据列
        rs = dbmd.getColumns(null, null, "account", "%");
        System.out.println("--account表的全部数据列--");
        printResultSet(rs);
        conn.close();
    }

    public void printResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        // 打印ResultSet的所有列标题
        for (int i = 0; i < rsmd.getColumnCount(); i++) {
            System.out.print(rsmd.getColumnName(i + 1) + "\t");
        }
        System.out.print("\n");
        // 打印ResultSet里的全部数据
        while (rs.next()) {
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                System.out.print(rs.getString(i + 1) + "\t");
            }
            System.out.print("\n");
        }
        rs.close();
    }

    public static void main(String[] args)  throws Exception {
        DatabaseMetaDataMain dt = new DatabaseMetaDataMain();
        dt.info();
    }
}
