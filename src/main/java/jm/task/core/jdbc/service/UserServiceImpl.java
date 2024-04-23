package jm.task.core.jdbc.service;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
//import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;
public class UserServiceImpl implements UserService {
    //private UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
    private UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
    public UserServiceImpl() {
        this.userDaoHibernate = new UserDaoHibernateImpl();
    }
    @Override
    public void createUsersTable() {
        userDaoHibernate.createUsersTable();
    }
    @Override
    public void dropUsersTable() {
        userDaoHibernate.dropUsersTable();
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDaoHibernate.saveUser(name, lastName, age);
    }
    @Override
    public void removeUserById(long id) {
        userDaoHibernate.removeUserById(id);
    }
    @Override
    public List < User > getAllUsers() {
        userDaoHibernate.getAllUsers();
        return userDaoHibernate.getAllUsers();
    }
    @Override
    public void cleanUsersTable() {
        userDaoHibernate.cleanUsersTable();
    }
}