package com.uptoser.java.javase.jdbc;

import java.sql.*;

/*
JDBC使用ResultSet来封装执行查询得到的查询结果，然后通过移动ResultSet的记录指针来取出结果集的内容。
除此之外，JDBC还允许通过ResultSet来更新记录，并提供了ResultSetMetaData来获得ResultSet对象的相关信息
可以使用absolute()、previous()、afterLast()等方法自由移动记录指针的ResultSet被称为可滚动的结果集。
以默认方式打开的ResultSet是不可更新的，
如果希望创建可更新的ResultSet，则必须在创建Statement或PreparedStatement时传入额外的参数。
➢ resultSetType：控制ResultSet的类型，该参数可以取如下三个值。
    • ResultSet.TYPE_FORWARD_ONLY：该常量控制记录指针只能向前移动。这是JDK 1.4以前的默认值。
    • ResultSet.TYPE_SCROLL_INSENSITIVE：该常量控制记录指针可以自由移动（可滚动结果集），但底层数据的改变不会影响ResultSet的内容。
    • ResultSet.TYPE_SCROLL_SENSITIVE：该常量控制记录指针可以自由移动（可滚动结果集），而且底层数据的改变会影响ResultSet的内容
➢ resultSetConcurrency：控制ResultSet的并发类型，该参数可以接收如下两个值。
    • ResultSet.CONCUR_READ_ONLY：该常量指示ResultSet是只读的并发模式（默认）。
    • ResultSet.CONCUR_UPDATABLE：该常量指示ResultSet是可更新的并发模式。
*/
public class ResultSetMain {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://192.168.3.200:3306/employees"
                , "mysql", "mysql");
        //创建了一个PreparedStatement对象，由该对象生成的ResultSet对象将是可滚动、可更新的结果集
        String sql = "select * from account";
        /*
        需要指出的是，可更新的结果集还需要满足如下两个条件。
        ➢ 所有数据都应该来自一个表。
        ➢ 选出的数据集必须包含主键列。
         */
        PreparedStatement stmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stmt.executeQuery();
        rs.last();
        for (int i = rs.getRow(); i > 0; i--) {
            rs.absolute(i);
            System.out.printf("%d\t%s\t%s\n",rs.getInt(1),rs.getString(2),rs.getString(3));

            if(rs.getString(2).equals("D")){
                //修改指针记录的值
                rs.updateString(2,"D"+rs.getString(2));
                //提交修改
                rs.updateRow();
            }
        }
        rs.close();
        stmt.close();
        conn.close();

    }
}
