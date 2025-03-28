import java.sql.*;

public class SQLConnection {
    
    Connection sql;

    public SQLConnection(String url, String user, String pass) {
        // Database connection
        try {
            this.sql = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
