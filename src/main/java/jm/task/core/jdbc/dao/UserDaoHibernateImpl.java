package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            String create = "CREATE TABLE IF NOT EXISTS Users ("
                    + "id BIGINT NOT NULL AUTO_INCREMENT,"
                    + "name VARCHAR(45) NOT NULL,"
                    + "lastName VARCHAR(45) NOT NULL,"
                    + "age TINYINT NOT NULL, PRIMARY KEY(id))";
            session.createSQLQuery(create).executeUpdate();
            transaction.commit();
            System.out.println("Successfully create table...");
        } catch (Exception q) {
            q.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            String drop = "DROP TABLE IF EXISTS Users";
            session.createSQLQuery(drop).executeUpdate();
            transaction.commit();
            System.out.println("Successfully drop table...");
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            String save = "INSERT INTO Users (name, lastName, age) VALUES (?, ?, ?)";
            SQLQuery query = session.createSQLQuery(save);
            query.setParameter(1, name);
            query.setParameter(2, lastName);
            query.setParameter(3, age);
            query.executeUpdate();
            transaction.commit();
            System.out.println("Successfully saveUser table...");
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            String delete = "DELETE FROM Users WHERE id = ?";
            session.createSQLQuery(delete);
            SQLQuery query = session.createSQLQuery(delete);
            query.setParameter(1, id);
            query.executeUpdate();
            transaction.commit();
            System.out.println("Successfully remove from table...");
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        String get = "SELECT * FROM Users";
        List<User> users = new ArrayList<>();
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(get).addEntity(User.class);
            users = query.list();
            for (Iterator<User> it = users.iterator(); it.hasNext();) {
                User us = (User) it.next();
            }
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction;
        String clean = "TRUNCATE TABLE Users";
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.createSQLQuery(clean).executeUpdate();
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
