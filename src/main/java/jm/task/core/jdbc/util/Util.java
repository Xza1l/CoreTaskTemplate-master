package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static final String URL = "jdbc:mysql://localhost:3306/testJDBC";
    public static final String NAME = "root";
    public static final String PASSWORD = "1234";

    public static Connection getConnect() {
        Connection connect = null;
        try {
            connect = DriverManager.getConnection(URL, NAME, PASSWORD);
            if (!connect.isClosed()){
                System.out.println("Успешное подключение!!!");
            }
            connect.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("no");
        }
        return connect;
    }
}
