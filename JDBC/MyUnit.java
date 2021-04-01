import java.sql.*;

public class MyUnit {

    //连接资源
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=root&useUnicode=true&characterEncoding=UTF-8&useSSL=false");
    }
    //关闭资源
    public static void closeResource(Connection c, PreparedStatement ps){
        //关闭资源，先小往大关。
        try {
            if(ps != null)
                ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (c != null)
                c.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void closeResource(Connection c, PreparedStatement ps, ResultSet resultSet){
        //关闭资源，先小往大关。
        try {
            if(resultSet != null)
                resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if(ps != null)
                ps.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (c != null)
                c.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
