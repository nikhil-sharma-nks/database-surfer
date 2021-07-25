import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Insert {
    String url = "jdbc:mysql://localhost:3306/user";
    String uname = "root";
    String pass = "1212";
    Connection con;

    {
        try {
            con = DriverManager.getConnection(url,uname,pass);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}