import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Test{
    public static void main(String[] args){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {//1.建立连接数据库
            //第一小步：加载JDBC驱动程序：反射，这样调用初始化com.mysql.jdbc.Driver类
            //即将该类加载到JVM方法区，并执行该类的静态方法快、静态属性
            Class.forName("com.mysql.jdbc.Driver");

            //创建数据库连接
            //Mysql数据连接的URL参数格式如下：
            //jdbc:mysql://服务器地址:端口/数据库名?参数名=参数值
            connection =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/test11122?" +
                            "user=root&password=root&useUnicode=true&characterEncoding=UTF-8&useSSL=false");
            System.out.println(connection);
            //2.创建操作命令(Statement)
            statement = connection.createStatement();

            //3.执行SQL语句
            resultSet = statement.executeQuery("select staff_id,name,age,depart_id from staff");

            //4.处理结果集
            List<Staff> list = new ArrayList<>();
            while (resultSet.next()) {
                int staff_id = resultSet.getInt("staff_id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                int depart_id = resultSet.getInt("depart_id");
                System.out.printf("staff_id=%s name=%s age=%s depart_id=%s", staff_id, name, age, depart_id);
                System.out.println();
                Staff staff = new Staff();
                staff.setStaff_id(staff_id);
                staff.setName(name);
                staff.setAge(age);
                staff.setDepart_id(depart_id);
                list.add(staff);
            }
            System.out.println(list);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //5.无论如何，都需要释放资源
            try {
                if(resultSet != null)
                    resultSet.close();
                if(statement != null)
                    statement.close();
                if(connection != null)
                    connection.close();
            }catch (SQLException throwables){
                throwables.printStackTrace();
            }
        }
    }
    private static class Staff{
        private Integer staff_id;
        private String name;
        private Integer age;
        private Integer depart_id;

        @Override
        public String toString() {
            return "Staff{" +
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
