package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
//import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            Util.getSessionFactory();
            System.out.println("Соединение установлено");
        } catch (HibernateException e) {
            System.out.println("Соединение не установлено");
            e.printStackTrace();
        }
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();
        userDao.saveUser("Name1", "LastName1", (byte) 20);
        userDao.saveUser("Name2", "LastName2", (byte) 25);
        userDao.saveUser("Name3", "LastName3", (byte) 31);
        userDao.saveUser("Name4", "LastName4", (byte) 38);
        userDao.removeUserById(2);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}