package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    public static final String URL = "jdbc:mysql://localhost:3306/testJDBC";
    public static final String USER = "root";
    public static final String PASS = "1234";

    public static final String DIALECT = "org.hibernate.dialect.MySQL8Dialect";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String SHOW_SQL = "true";
    public static final String CURRENT_SESSION_CONTEXT_CLASS = "thread";
    public static final String HBM2DDL_AUTO = "create-drop";


    public static Connection getConnect() {
        Connection connect = null;
        try {
            connect = DriverManager.getConnection(URL, USER, PASS);
            if (!connect.isClosed()){
                System.out.println("Успешное подключение!!!");
            }
            connect.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("no");
        }
        return connect;
    }

    public static SessionFactory getSessionFactory(){
        SessionFactory sessionFactory = null;
        try {
            Configuration configuration = new Configuration();
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, DRIVER);
            settings.put(Environment.URL, URL);
            settings.put(Environment.USER, USER);
            settings.put(Environment.PASS, PASS);
            settings.put(Environment.DIALECT, DIALECT);
            settings.put(Environment.SHOW_SQL, SHOW_SQL);
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, CURRENT_SESSION_CONTEXT_CLASS);
            settings.put(Environment.HBM2DDL_AUTO, HBM2DDL_AUTO);

            configuration.setProperties(settings);

            configuration.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            System.out.println("Successfully connect");

        }catch (Exception e){
            e.printStackTrace();
        }
        return sessionFactory;
    }
}
