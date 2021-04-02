import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class PreparedStatementBlob2 {
    public static void main(String[] args) {
        Connection c = null;
        PreparedStatement ps = null;
        InputStream is = null;
        FileOutputStream fos = null;
        ResultSet resultSet = null;
        try {
            c = MyUnit.getConnection();
            String sql = " select photo from star where name = ?;";
            ps = c.prepareStatement(sql);
            ps.setString(1,"张国荣");
            resultSet = ps.executeQuery();

            while(resultSet.next()){
                Blob blob = resultSet.getBlob(1);
                is = blob.getBinaryStream();
                fos = new FileOutputStream("lib/zhangguorong.jpeg");
                byte[] buffer = new byte[1024];
                int len;
                while((len = is.read(buffer)) != -1){
                    fos.write(buffer,0,len);
                }
            }
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            MyUnit.closeResource(c,ps,resultSet);
        }
    }
}
