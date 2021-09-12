import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;

public class ThirtySecond {
    public static void main(String[] args) throws Exception {
        Connection connection = null;
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8","root","123456");
        String sql = "insert into users(username,password) values('myseq.nextval','#name#')";
        Statement statement = connection.createStatement();
        for (int x = 0 ; x < 10 ; x++){
            statement.addBatch(sql.replaceFirst("#name#","mldn - " + x));
        }
        int result[] = statement.executeBatch();
        System.out.println(Arrays.toString(result));
        connection.close();
    }
}
