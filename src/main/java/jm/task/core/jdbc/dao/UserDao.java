package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;

public interface UserDao {
    void createUsersTable() throws Exception;

    void dropUsersTable() throws Exception;

    void saveUser(String name, String lastName, byte age) throws Exception;

    void removeUserById(long id) throws Exception;

    List<User> getAllUsers() throws Exception;

    void cleanUsersTable() throws Exception;
}
