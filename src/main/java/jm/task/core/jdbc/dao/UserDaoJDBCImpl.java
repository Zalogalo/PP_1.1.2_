package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Statement statement = Util.getStatement();) {
            statement.execute("CREATE TABLE IF NOT EXISTS users (id BIGINT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(45)," +
                    " lastName VARCHAR(45)," +
                    " age TINYINT, PRIMARY KEY (id));");
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            System.err.println("Таблица не создана");
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Statement statement = Util.getStatement();) {
            statement.execute("DROP TABLE IF EXISTS users;");
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            System.err.println("Не удалось удалить таблицу");
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.err.println("Не удалось сохранить пользователя");
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь с ID: " + id + " удален из базы данных");
        } catch (SQLException e) {
            System.err.println("Не удалось удалить пользователя с ID: " + id);
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Statement statement = Util.getStatement();) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                userList.add(new User(name, lastName, age));
                System.out.println("Пользователь с ID: " + id + " получен");
            }
        } catch (SQLException e) {
            System.out.println("Не удалось получить список пользователей");
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Statement statement = Util.getStatement();) {
            statement.execute("DELETE from users");
            System.out.println("Таблица очищена");
        } catch (SQLException e) {
            System.err.println("Не удалось очистить таблицу");
            e.printStackTrace();
        }
    }
}
