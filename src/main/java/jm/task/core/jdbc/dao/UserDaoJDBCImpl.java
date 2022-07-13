package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connect = Util.getConnect();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String exc = "CREATE TABLE IF NOT EXISTS Users ("
                + "id BIGINT NOT NULL AUTO_INCREMENT,"
                + "name VARCHAR(45) NOT NULL,"
                + "lastName VARCHAR(45) NOT NULL,"
                + "age TINYINT NOT NULL, PRIMARY KEY(id))";
        try (Statement statement = connect.createStatement()) {
            statement.execute(exc);
            connect.commit();
        } catch (SQLException e) {
            try {
                connect.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String drop = "DROP TABLE IF EXISTS Users";
        try {
            Statement statement = connect.createStatement();
            statement.execute(drop);
            connect.commit();
        } catch (SQLException e) {
            try {
                connect.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String insert = "INSERT INTO Users (name, lastName, age) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connect.prepareStatement(insert)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            connect.commit();
        } catch (SQLException e) {
            try {
                connect.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String alter = "ALTER TABLE Users DROP id";
        try (PreparedStatement preparedStatement = connect.prepareStatement(alter)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            connect.commit();
        } catch (SQLException e) {
            try {
                connect.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        String getAll = "SELECT * FROM Users";
        try (Statement statement = connect.createStatement()){
            ResultSet resultSet = statement.executeQuery(getAll);
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                byte age = resultSet.getByte(4);
                System.out.printf("%d %s %s %d", id, name, lastName, age);
            }
            connect.commit();
        } catch (SQLException e) {
            try {
                connect.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        return null;
    }

    public void cleanUsersTable() {
        String clean = "TRUNCATE TABLE Users";
       try (Statement statement = connect.createStatement()){
           statement.execute(clean);
           connect.commit();
       } catch (SQLException e) {
           try {
               connect.rollback();
           } catch (SQLException ex) {
               throw new RuntimeException(ex);
           }
           throw new RuntimeException(e);
       }
    }
}
