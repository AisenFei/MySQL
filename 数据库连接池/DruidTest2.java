import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DruidTest2 {
    public static void main(String[] args) throws Exception {
        Properties p = new Properties();
        InputStream is = ClassLoader.getSystemResourceAsStream("druid.properties");
        p.load(is);
        DataSource ds = DruidDataSourceFactory.createDataSource(p);
        Connection c = ds.getConnection();
        System.out.println(c);
    }
}
