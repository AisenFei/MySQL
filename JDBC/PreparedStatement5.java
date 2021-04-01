import java.lang.reflect.Field;
import java.sql.*;

class Person {
    private int id;
    private String username;
    private double balance;

    public Person(){
        super();
    }

    public Person(int id, String username, double balance) {
        super();
        this.id = id;
        this.username = username;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", balance=" + balance +
                '}';
    }
}

public class PreparedStatement5 {
    public static void main(String[] args) {
        String sql = "select id,username,balance from account where id = ?;";
        Person person = search(sql,1);
        System.out.println(person);
    }

    public static Person search(String sql, Object... args) {
        Connection c = null;
        PreparedStatement ps = null;

        ResultSet resultSet = null;
        try {
            c = MyUnit.getConnection();
            ps = c.prepareStatement(sql);

            //设置占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            //返回结果集
            resultSet = ps.executeQuery();
            //获取结果集元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            //获取结果集列数
            int columnCount = metaData.getColumnCount();

            if (resultSet.next()) {
                Person person = new Person();
                for (int i = 0; i < columnCount; i++) {
                    //获取数据
                    Object object = resultSet.getObject(i + 1);
                    //获取列名
                    String columnName = metaData.getColumnName(i + 1);
                    //通过反射找到对应的属性
                    Field declaredField = Person.class.getDeclaredField(columnName);
                    //防止属性为private
                    declaredField.setAccessible(true);
                    //设置属性值
                    declaredField.set(person, object);
                }
                return person;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyUnit.closeResource(c, ps, resultSet);
        }
        return null;
    }
    //a' OR 1 =
    public static void search2(String username,String password){
        String sql = "SELECT user, password FROM user_table WHERE user='"+username+"' AND password = '"+password+"'";
    }
}
