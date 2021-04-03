import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;

public class C3P0Test {
    public static void main(String[] args) throws Exception{
        //创建数据库连接池对象
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        //设置驱动类
        cpds.setDriverClass("com.mysql.jdbc.Driver");
        //设置JDBCURL
        cpds.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        cpds.setUser("root");
        cpds.setPassword("root");

        //通过设置相关的
        //设置初始时数据库连接池的连接数
        cpds.setInitialPoolSize(10);
        //获取连接
        Connection c = cpds.getConnection();
        System.out.println(c);
    }
}
