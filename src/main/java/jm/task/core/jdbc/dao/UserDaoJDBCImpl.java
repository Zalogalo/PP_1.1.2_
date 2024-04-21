package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws Exception {
        try (Statement statement = Util.getStatement();) {
            statement.execute("CREATE TABLE IF NOT EXISTS users (id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR(45), lastName VARCHAR(45), age TINYINT, PRIMARY KEY (id));");
        } catch (SQLException e) {
            throw new Exception("Произошла ошибка при создании таблицы");
        }
    }

    @Override
    public void dropUsersTable() throws Exception {
        try(Statement statement = Util.getStatement();) {
            statement.execute("DROP TABLE IF EXISTS users;");
        } catch (SQLException e) {
            throw new Exception("Произошла ошибка при удалении таблицы");
        }
    }

    public void saveUser(String name, String lastName, byte age) throws Exception {
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Произошла ошибка при сохранении пользователя");
        }
    }

    public void removeUserById(long id) throws Exception {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Произошла ошибка при удалении пользователя");
        }
    }

    public List<User> getAllUsers() throws Exception {
        List<User> userList = new ArrayList<>();
        try(Statement statement = Util.getStatement();) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                userList.add(new User(name, lastName, age));
            }
        } catch (SQLException e) {
            throw new Exception("Произошла ошибка при получении списка пользователей");
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() throws Exception {
        try(Statement statement = Util.getStatement();) {
            statement.execute("DELETE from users");
        } catch (SQLException e) {
            throw new Exception("Произошла ошибка при очистке таблицы");
        }
    }
}
