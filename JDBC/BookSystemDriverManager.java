package org.example;

import java.sql.*;

public class BookSystemDriverManager {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            //加载JDBC 的驱动程序--加载的是哪个类？ Connection 接口？DriverManager？
            Class.forName("com.mysql.jdbc.Driver");

            //创建数据库连结--连接到本地数据库-DriverManage使用的比较少
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebook?" +
                    "user=root&password=root" +
                    "&useUnicode=true&characterEncoding=UTF-8&" +
                    "useSSL=false");
            System.out.println(connection);//打印连接

            //创建操作命令对象statement --改进的是prepared statement
            statement = connection.createStatement();

            //执行sql
            String sql = "select * from category";
            resultSet = statement.executeQuery(sql);

            //处理结果集
            while(resultSet.next()) {
                //
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.format("id = %d,name = %s%n",id,name);
            }
        } catch (Exception e) {
        } finally {
            //反向释放资源
            try {
                if(resultSet != null) {
                    resultSet.close();
                }
                if(statement != null) {
                    statement.close();
                }
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }








    }



}
