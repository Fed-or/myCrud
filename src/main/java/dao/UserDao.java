package dao;

import model.User;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;


public class UserDao {
    private static final Map<String, User> USERS = new HashMap<>();
    private static dao.UserDao instance;

    private UserDao() {
    }

    public static synchronized dao.UserDao getInstance() {
        if (instance == null) {
            instance = new dao.UserDao();
        }
        return instance;
    }

    public boolean addUser(User user) {
        Connection connection = DbConnector.connect();
        try {
            Statement statement = connection.createStatement();
            statement.execute("create TABLE IF NOT EXISTS users (id BIGINT not null auto_increment, name VARCHAR(255), password VARCHAR(255), PRIMARY KEY (id));");
            String sql = "INSERT INTO users(name, password) VALUES('" + user.getName()
                    + "','" + user.getPassword() + "');";
            statement.execute(sql);
            System.out.println(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    public User getUser(String name) {
        Connection connection = DbConnector.connect();
        User user = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users where name = '" + name + "' limit 1");
            while (resultSet.next()) {
                long idUser = resultSet.getInt("id");
                String userName = resultSet.getString("name");
                String userPass = resultSet.getString("password");
                user = new User(idUser, userName, userPass);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public long getId(String name) {
        Connection connection = DbConnector.connect();
        long idUser = 1;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users where id = '" + name + "' limit 1");
            while (resultSet.next()) {
                idUser = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idUser;
    }
}
