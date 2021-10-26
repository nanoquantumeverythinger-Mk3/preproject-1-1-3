package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    static private String url = "jdbc:mysql://localhost:3306/my_database?useSSL=false";
    static private String user = "root";
    static private String password = "password";
    static private Connection conn;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        return conn = DriverManager.getConnection(url, user, password);
    }
}
