package com.uptoser.java.javase.jdbc;

import org.junit.Test;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/**
 * Java 7新增了RowSetProvider类和RowSetFactory接口，其中RowSetProvider负责创建RowSetFactory，而RowSetFactory则提供了如下方法来创建RowSet实例。
 * ➢ JdbcRowSet createJdbcRowSet()：创建一个默认的JdbcRowSet。
 * ➢ CachedRowSet createCachedRowSet()：创建一个默认的CachedRowSet。
 * ➢ FilteredRowSet createFilteredRowSet()：创建一个默认的FilteredRowSet。
 * ➢ JoinRowSet createJoinRowSet()：创建一个默认的JoinRowSet。
 * ➢ WebRowSet createWebRowSet()：创建一个默认的WebRowSet。
 * 除JdbcRowSet需要保持与数据库的连接之外，其余4个子接口都是离线的RowSet，无须保持与数据库的连接
 */
public class RowSetMain {
    private final String url;
    private final String user;
    private final String pass;
    private final RowSetFactory factory;
    String sql = "select * from account";

    public RowSetMain() throws Exception {
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
        // 使用RowSetProvider创建RowSetFactory
        this.factory = RowSetProvider.newFactory();
    }

    @Test
    public void createJdbcRowSet() {
        try (JdbcRowSet jdbcRs = factory.createJdbcRowSet()) {
            // 设置必要连接信息
            jdbcRs.setUrl(url);
            jdbcRs.setUsername(user);
            jdbcRs.setPassword(pass);
            // 设置SQL查询语句
            jdbcRs.setCommand(sql);
            // 执行查询
            jdbcRs.execute();
            jdbcRs.afterLast();
            // 向前滚动结果集
            while (jdbcRs.previous()) {
                System.out.println(jdbcRs.getString(1)
                        + "\t" + jdbcRs.getString(2)
                        + "\t" + jdbcRs.getString(3));
                if (jdbcRs.getInt("id") == 3) {
                    // 修改指定记录行
                    jdbcRs.updateInt("balance", 200);
                    jdbcRs.updateRow();
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * 离线RowSet会直接将底层数据读入内存中，封装成RowSet对象
     * CachedRowSet是所有离线RowSet的父接口
     */
    @Test
    public void createCachedRowSet() throws SQLException {
        Connection conn = DriverManager.getConnection(url , user , pass);
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        // 创建默认的CachedRowSet实例
        CachedRowSet rs = factory.createCachedRowSet();
        // 使用ResultSet装填RowSet
        rs.populate(resultSet);
        // 关闭资源
        resultSet.close();
        statement.close();
        conn.close();
        //将ResultSet的记录指针定位到最后一行之后
        rs.afterLast();
        // 向前滚动结果集
        while (rs.previous()){
            System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
            if (rs.getInt("id") == 3){
                // 修改指定记录行
                rs.updateInt("balance", 100);
                rs.updateRow();
            }
        }
        // 重新获取数据库连接
        conn = DriverManager.getConnection(url , user , pass);
        conn.setAutoCommit(false);
        // 把对RowSet所做的修改同步到底层数据库
        rs.acceptChanges(conn);
        //close
        conn.close();
        rs.close();


    }

}
