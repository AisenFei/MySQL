import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BigInsert1 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection c = MyUnit.getConnection();
        String sql = "insert into people(name) values(?);";
        PreparedStatement ps = c.prepareStatement(sql);
        long begin = System.currentTimeMillis();
        for(int i = 0;i < 20000;i++){
            ps.setString(1,"name_"+i);
            ps.execute();
        }
        long end = System.currentTimeMillis();
        System.out.println(end-begin);
        MyUnit.closeResource(c,ps);
    }
}
