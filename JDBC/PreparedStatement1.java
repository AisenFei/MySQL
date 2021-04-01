import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedStatement1 {
    public static void main(String[] args) {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            //1.类加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取连接
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=root&useUnicode=true&characterEncoding=UTF-8&useSSL=false");
            //3.预编译SQL语句，返回PreparedStatement的实例
            String sql = "insert into account(username,balance) values(?,?);";
            ps = c.prepareStatement(sql);
            //4.填充占位符
            ps.setString(1,"阿里巴巴");
            ps.setInt(2,2000);
            //5.执行操作
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
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
    }
}
