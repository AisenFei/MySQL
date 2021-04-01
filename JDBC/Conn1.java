import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //第一步：建立数据库连接
        //加载JDBC驱动程序：这样调用初始化com.mysql.jdbc.Driver类
        //即将该类加载到JVM方法区，并执行该类的静态代码块、静态属性。
        Class.forName("com.mysql.jdbc.Driver");

        //创建数据库链接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=root&useUnicode=true&characterEncoding=UTF-8&useSSL=false");
        System.out.println(connection);


    }
}
