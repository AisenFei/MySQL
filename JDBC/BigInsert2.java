import java.sql.Connection;
import java.sql.PreparedStatement;

public class BigInsert2 {
    public static void main(String[] args) throws Exception {
        Connection c = MyUnit.getConnection();
        String sql = "insert into people(name) values(?)";
        PreparedStatement ps = c.prepareStatement(sql);
        long begin = System.currentTimeMillis();
        for(int i = 1;i <= 1000000;i++){
            ps.setString(1,"name_"+i);
            ps.addBatch();
            if(i % 500 == 0){
                ps.executeBatch();
                ps.clearBatch();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
        MyUnit.closeResource(c,ps);
    }
}
