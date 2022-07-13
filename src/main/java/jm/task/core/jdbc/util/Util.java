package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static final String URL = "jdbc:mysql://localhost:3306";
    public static final String NAME = "root";
    public static final String PASSWORD = "1234";

    public static Connection getConnect() {
        Connection connect = null;
        try {
            connect = DriverManager.getConnection(URL, NAME, PASSWORD);
            System.out.println(connect.isClosed());
        } catch (SQLException e) {
            System.out.println("NO");
        }
        return connect;
    }
}
