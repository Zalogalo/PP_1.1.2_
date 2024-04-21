package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            Util.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();
        System.out.println("Таблица создана");

        userDao.saveUser("Name1", "LastName1", (byte) 20);
        System.out.println("Пользователь сохранен");
        userDao.saveUser("Name2", "LastName2", (byte) 25);
        System.out.println("Пользователь сохранен");
        userDao.saveUser("Name3", "LastName3", (byte) 31);
        System.out.println("Пользователь сохранен");
        userDao.saveUser("Name4", "LastName4", (byte) 38);
        System.out.println("Пользователь сохранен");

        userDao.removeUserById(1);
        System.out.println("Пользователь удален");
        userDao.getAllUsers();
        System.out.println("Пользователи получены");
        userDao.cleanUsersTable();
        System.out.println("Таблица очищена");
        userDao.dropUsersTable();
        System.out.println("Таблица удалена");
    }
}
