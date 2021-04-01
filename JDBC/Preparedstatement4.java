import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Preparedstatement4 {
    //查询操作
    public static void main(String[] args) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            c = MyUnit.getConnection();
            String sql = "select id,username,balance from account where id = ?;";
            ps = c.prepareStatement(sql);
            ps.setInt(1,1);
            //返回结果集
            resultSet = ps.executeQuery();
            //处理结果集
            while(resultSet.next()){
                int id = resultSet.getInt(1);
                String username = resultSet.getString(2);
                int balance = resultSet.getInt(3);
                System.out.println("id="+id+",username="+username+",balance="+balance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            MyUnit.closeResource(c,ps,resultSet);
        }
    }
}
