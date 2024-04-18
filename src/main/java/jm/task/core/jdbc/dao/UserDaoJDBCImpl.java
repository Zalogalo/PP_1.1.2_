package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            Statement statement = Util.getStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS users (id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR(45), lastName VARCHAR(45), age TINYINT, PRIMARY KEY (id));");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try {
            Statement statement = Util.getStatement();
            statement.execute("DROP TABLE IF EXISTS users;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            Statement statement = Util.getStatement();
            statement.execute("INSERT INTO users (name, lastName, age) VALUES ('" + name + "', '" + lastName + "', " + age + ");");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try {
            Statement statement = Util.getStatement();
            statement.execute("DELETE FROM users WHERE id = " + id + ";");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            Statement statement = Util.getStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                userList.add(new User(name, lastName, age));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }
    public void cleanUsersTable() {
        try {
            Statement statement = Util.getStatement();
            statement.execute("DELETE from users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
