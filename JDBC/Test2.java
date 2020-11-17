import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Test2 {
    public static void main(String[] args){
        query("高飞");
    }

    //模拟文本框输入班级名称，查询信息；
    //实现一个方法，参数为传入的班级名称，返回类型为List<Info>
    public static List<Info> query(String name){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            //JDBC操作的第一步：创建连接
            //创建数据库连接
            MysqlDataSource ds = new MysqlDataSource();
            ds.setUrl("jdbc:mysql://localhost:3306/test11122?user=root&password=root&useUnicode=true&characterEncoding=UTF-8&useSSL=false");
            connection = ds.getConnection();
            System.out.println(connection);

            //第二步：创建操作命令对象Statement，并且执行操作语句
            String sql = "select staff_id,name,age,depart_id from staff where name=?";
            //PreparedStatement预编译的操作命令对象：注意使用String sql传入参数
            //发送sql，让数据库预编译：语法分析，执行顺序分析，执行优化
            statement = connection.prepareStatement(sql);
            //替换占位符：指定占位符的位置（从1开始），数据类型
            statement.setString(1,name);
            //如果执行SQL有报错，一定要先打印或者是debug看看执行sql语句是否有误
            System.out.println(statement);
            //预编译的操作命令对象PreparedStatement一定要使用无参的方法
            resultSet = statement.executeQuery();

            List<Info> list = new ArrayList<>();
            //处理结果集(查询操作)
            while(resultSet.next()){
                int staff_id = resultSet.getInt("staff_id");
                String name1 = resultSet.getString("name");
                int age = resultSet.getInt("age");
                int depart_id = resultSet.getInt("depart_id");
                System.out.printf("staff_id=%s name=%s age=%s depart_id=%s", staff_id, name, age, depart_id);
                System.out.println();
                Test2.Info info = new Test2.Info();
                info.setStaff_id(staff_id);
                info.setName(name1);
                info.setAge(age);
                info.setDepart_id(depart_id);
                list.add(info);
            }
            System.out.println(list);
            return list;
        } catch (Exception throwables) {
            throwables.printStackTrace();
            //执行某个功能，如果出现异常，建议再次抛出异常
            throw new RuntimeException("查询出错",throwables);
        }finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if(statement!=null)
                    statement.close();
                if(connection!=null)
                    connection.close();
            }catch(SQLException throwables){
                throwables.printStackTrace();
            }
        }
    }
    private static class Info{
        private Integer staff_id;
        private String name;
        private Integer age;
        private Integer depart_id;

        @Override
        public String toString() {
            return "Info{" +
                    "staff_id=" + staff_id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    ", depart_id=" + depart_id +
                    '}';
        }

        public Integer getStaff_id() {
            return staff_id;
        }

        public void setStaff_id(Integer staff_id) {
            this.staff_id = staff_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Integer getDepart_id() {
            return depart_id;
        }

        public void setDepart_id(Integer depart_id) {
            this.depart_id = depart_id;
        }
    }
}

