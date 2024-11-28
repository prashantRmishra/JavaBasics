package ObjectPool;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection{
    private Connection connection;
    public Connection getConnection(){
        try {
            connection = DriverManager.getConnection("url", "username", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}