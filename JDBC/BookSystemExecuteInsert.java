package org.example;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookSystemExecuteInsert {
    //日期字符串转Java日期类Date和sql时间戳Timestamp

//    public static Timestamp getTimestamp(String dateString){
//
//        try {
//
//            // 年-月-日 小时:分钟:秒
//
//            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
//
//            return new java.sql.Timestamp(date.getTime());
//
//        } catch (ParseException e) {
//
//            throw new RuntimeException("日期格式化错误："+dateString, e);
//
//        }
//
//    }


    public static void main(String[] args) throws SQLException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            //加载jdbc 驱动程序
            //创建数据库连接 -- 合二为一
            MysqlDataSource ds = new MysqlDataSource();
            ds.setUrl("jdbc:mysql://localhost:3306/ebook?" +
                    "user=root&password=root" +
                    "&useUnicode=true&characterEncoding=UTF-8&" +
                    "useSSL=false");
            connection = ds.getConnection();
            System.out.println(connection); // 打印这个语句的意义是什么

            //创建操作命令对象
            /**
             * 新增貂蝉同学的借阅记录
             */
//            String sql1 = "insert into borrow_info (book_id, student_id,start_time, end_time)  " +
//                    " select b.id,s.id,?, ?  " +
//                    " from book b,student s  " +
//                    " where b.name=? and s.name=?; ";
//
//             preparedStatement = connection.prepareStatement(sql1);//是执行 sql1 同时还有预编译的功能
//
//            //preparedStatement.setTimestamp(1,"2019-09-25 17:50:00"); 时间不能直接写，许哟啊转换日期格式
//
//            preparedStatement.setTimestamp(1, Timestamp.valueOf("2019-09-25 17:50:00"));
//            preparedStatement.setTimestamp(2, Timestamp.valueOf("2019-10-25 17:50:00"));
//
//            preparedStatement.setString(3, "诗经");
//
//            preparedStatement.setString(4, "貂蝉");
//
//            System.out.println(preparedStatement);

            //执行sql--如果创建多个sql 执行哪一个？
//            int r = preparedStatement.executeUpdate();
//            System.out.println(r);

            /**
             * 删除id最大的一条借阅记录
             */

            String sql2 = "delete " +
                    "from borrow_info " +
                    "where id =" +
                    "( select r.id " +
                    "from " +
                    "(" +
                    "select max(id) id " +
                    "from borrow_info )r  " +
                    "); ";

            preparedStatement = connection.prepareStatement(sql2);
            //意思是我没有占位符就不执行这个语句吗，我还非得传入一个占位符吗？
            //不过这个本身的意义就是预编译，--不是就算没有占位符还是会预编译。
            System.out.println(preparedStatement);

            int result = preparedStatement.executeUpdate();

        } catch (Exception e) {
        } finally {
            try {
                if(preparedStatement != null) {
                    preparedStatement.close();
                }
                if(connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }


}
