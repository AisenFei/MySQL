import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.SQLException;

public class DruidTest {
    public static void main(String[] args) throws SQLException {
        DruidDataSource dds = new DruidDataSource();
        dds.setDriverClassName("com.mysql.jdbc.Driver");
        dds.setUrl("jdbc:mysql://localhost:3306/test");
        dds.setUsername("root");
        dds.setPassword("root");

        DruidPooledConnection c = dds.getConnection();
        System.out.println(c);
    }
}
