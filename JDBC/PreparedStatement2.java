import java.sql.Connection;
import java.sql.PreparedStatement;

public class PreparedStatement2 {
    //修改练习
    public static void main(String[] args) {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = MyUnit.getConnection();
            String sql = "update account set balance = ? where username = ?;";
            ps = c.prepareStatement(sql);

            ps.setInt(1,100);
            ps.setString(2,"阿里巴巴");

            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            MyUnit.closeResource(c,ps);
        }
    }
}
