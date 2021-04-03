import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;

public class C3P0Test2 {
    public static void main(String[] args) throws Exception{
        DataSource cpds = new ComboPooledDataSource("helloc3p0");
        Connection c = cpds.getConnection();
        System.out.println(c);
    }
}
