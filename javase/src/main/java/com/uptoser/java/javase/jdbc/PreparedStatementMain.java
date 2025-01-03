package com.uptoser.java.javase.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 如果经常需要反复执行一条结构相似的SQL语句，例如：
 * INSERT INTO account(account,balance) VALUES ('C','100');
 * INSERT INTO account(account,balance) VALUES ('D','100');
 *
 * 对于这两条SQL语句而言，它们的结构基本相似，只是执行插入时插入的值不同而已。对于这种情况，可以使用带占位符（?）参数的SQL语句来代替它
 * INSERT INTO account(account,balance) VALUES (?,?);
 * 为了满足这种功能，JDBC提供了PreparedStatement接口，它是Statement接口的子接口，它可以预编译SQL语句，
 * 预编译后的SQL语句被存储在PreparedStatement对象中，然后可以使用该对象多次高效地执行该语句
 * PreparedStatement也提供了execute()、executeUpdate()、executeQuery()三个方法来执行SQL语句，
 * 不过这三个方法无须参数，因为PreparedStatement已存储了预编译的SQL语句
 *
 *
 */
public class PreparedStatementMain {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://192.168.3.200:3306/employees"
                , "mysql" , "mysql");
        String sql = "INSERT INTO account(account,balance) VALUES (?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        //添加10条数据
        for (int i = 1; i <= 10; i++) {
            stmt.setString(1, String.valueOf((char)(67+i)));
            stmt.setInt(2,0);
            System.out.println("第"+i+"条的执行状态："+(stmt.executeUpdate()>0?true:false));
        }
        stmt.close();
        conn.close();
    }

}
