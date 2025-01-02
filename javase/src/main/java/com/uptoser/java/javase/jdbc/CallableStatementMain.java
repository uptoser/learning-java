package com.uptoser.java.javase.jdbc;

import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * CallableStatement： 调用存储过程
 * 可以通过Connection的prepareCall()方法来创建CallableStatement对象，创建该对象时需要传入调用存储过程的SQL语句{call过程名(?, ?, ?...)}
 * 可以通过CallableStatement的setXxx()方法为传入参数
 * 可以通过CallableStatement的registerOutParameter()方法来注册传出参数
 */
public class CallableStatementMain {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://192.168.3.200:3306/mybatis-test"
                , "mysql" , "mysql");
        String sql = "call count_role(?,?,?)";
        CallableStatement stmt = conn.prepareCall(sql);
        //设置传入参数
        stmt.setString(1, "role");
        //设置传出参数
        stmt.registerOutParameter(2,Types.INTEGER);
        stmt.registerOutParameter(3, Types.DATE);
        //执行
        stmt.execute();
        //获取返回参数
        System.out.println("角色的数量为："+stmt.getInt(2));
        System.out.println("日期为："+new SimpleDateFormat("yyyy-MM-dd").format(stmt.getDate(3)));
        conn.close();
    }
}
