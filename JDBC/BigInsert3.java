import java.sql.Connection;
import java.sql.PreparedStatement;

public class BigInsert3 {
    public static void main(String[] args) throws Exception{
        Connection c = MyUnit.getConnection();

        //设置为不自动提交数据
        c.setAutoCommit(false);

        String sql = "insert into people(name) values(?)";
        PreparedStatement ps = c.prepareStatement(sql);
        long begin = System.currentTimeMillis();
        for(int i = 1;i <= 1000000;i++){
            ps.setString(1,"name_"+i);
            //"攒"sql
            ps.addBatch();

            if(i % 500 == 0){
                //执行
                ps.executeBatch();
                //清空
                ps.clearBatch();
            }
        }
        //提交数据
        c.commit();
        long end = System.currentTimeMillis();
        System.out.println(end - begin);

        MyUnit.closeResource(c,ps);
    }
}
