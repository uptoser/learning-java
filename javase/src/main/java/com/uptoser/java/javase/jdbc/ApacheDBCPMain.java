package com.uptoser.java.javase.jdbc;


import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;

/**
 * DBCP连接池是Apache软件基金组织下的开源连接池实现，该连接池依赖该组织下的另一个开源系统：common-pool
 */
public class ApacheDBCPMain {
    public final static BasicDataSource ds;
    static {
        ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://192.168.3.200:3306/employees");
        ds.setUsername("mysql");
        ds.setPassword("mysql");
        //设置连接池的初始连接数
        ds.setInitialSize(5);
        //设置连接池最多可有多少个活动连接数
        ds.setMaxActive(20);
        //设置连接池中最少有几个空闲的连接
        ds.setMinIdle(2);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                try(
                    Connection conn = ApacheDBCPMain.ds.getConnection();
                    Statement statement = conn.createStatement();
                    ResultSet resultSet = statement.executeQuery("select UNIX_TIMESTAMP()");
                ){
                    resultSet.next();
                    Long time = resultSet.getLong(1);
                    System.out.println(Thread.currentThread().getName()+"完成时间点为："+Instant.ofEpochMilli(time));
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
