import java.sql.Connection;
import java.sql.PreparedStatement;

public class PreparedStatement3 {
    public static void main(String[] args) {
        String sql = "delete from account where username = ?;";
        update(sql,"阿里巴巴");
    }

    public static void update(String sql,Object ...args){//可变形参
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = MyUnit.getConnection();
            ps = c.prepareStatement(sql);
            for(int i = 0;i < args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            ps.execute();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            MyUnit.closeResource(c,ps);
        }
    }
}
