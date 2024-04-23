package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    public static Statement getStatement() throws SQLException {
        return getConnection().createStatement();
    }
    //Configuration configuration = new Configuration().addAnnotatedClass(User.class);
    //SessionFactory sessionFactory = configuration.buildSessionFactory();
    //Session session = sessionFactory.getCurrentSession();
}