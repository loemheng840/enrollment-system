package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/Enrollment";
    private static final String USER = "postgres";
    private static final String PASS = "8990";
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try{
                connection = DriverManager.getConnection(URL,USER,PASS);
                System.out.println("Connected to database");
            }catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return connection;
    }
}
