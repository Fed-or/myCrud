package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static dao.UserDao instance;
    private Connection dbConnection = DbConnector.getDbConnection();

    private UserDao() {
    }

    public static synchronized dao.UserDao getInstance() {
        if (instance == null) {
            instance = new dao.UserDao();
        }
        return instance;
    }

    public boolean addUser(User user) {
        String addUserIntoTable = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT not null auto_increment, " +
                "firstName VARCHAR(45), lastName VARCHAR(45), login varchar(45), " +
                "pass VARCHAR(45), address VARCHAR(45), email VARCHAR(45), PRIMARY KEY (id));";

        String insertPropertiesUserSql = "INSERT INTO users (firstName, lastName, login, pass, address, email) VALUES ('?','?','?','?','?','?')";

        try (PreparedStatement preparedStatement =
                     dbConnection.prepareStatement(addUserIntoTable)) {
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        try (PreparedStatement preparedStatement =
                     dbConnection.prepareStatement(insertPropertiesUserSql)) {
            if (preparedStatement != null) {
                preparedStatement.setString(1, user.getFirstName());
                preparedStatement.setString(2,user.getLastName());
                preparedStatement.setString(3, user.getLogin());
                preparedStatement.setString(4, user.getPassword());
                preparedStatement.setString(5, user.getAddress());
                preparedStatement.setString(6, user.getEmail());
                preparedStatement.executeQuery();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
}

    public User getUserById(long id) {
        User user = null;
        String selectFromTableSql = "select * from users " +
                "where id = ?";
        try (PreparedStatement preparedStatement =
                     dbConnection.prepareStatement(selectFromTableSql)) {
            if (preparedStatement != null) {
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    long idUser = resultSet.getLong("id");
                    String userFirstName = resultSet.getString("firstName");
                    String userLastName = resultSet.getString("lastName");
                    String userLogin = resultSet.getString("login");
                    String userPass = resultSet.getString("pass");
                    String userAddress = resultSet.getString("address");
                    String userEmail = resultSet.getString("email");
                    user = new User(idUser, userFirstName, userLastName, userLogin, userPass, userAddress, userEmail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public int getIdByName(String firstName, String lastName) {
        int idUser = 1;
        String selectUserByNameSql = "select * from users where id = ? AND id = ?";
        try (PreparedStatement preparedStatement =
                     dbConnection.prepareStatement(selectUserByNameSql)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                idUser = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idUser;
    }

    public User getUserByLoginAndPass(String log, String password) {
        User user = null;
        String selectFromTable = "select * from users where login=? AND pass=?";
        try (PreparedStatement preparedStatement =
                     dbConnection.prepareStatement(selectFromTable)) {
            if (preparedStatement != null) {
                preparedStatement.setString(1, log);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int idUser = resultSet.getInt("id");
                    String userFirstName = resultSet.getString("firstName");
                    String userLastName = resultSet.getString("lastName");
                    String userLogin = resultSet.getString("login");
                    String userPass = resultSet.getString("pass");
                    String userAddress = resultSet.getString("address");
                    String userEmail = resultSet.getString("email");
                    user = new User(idUser, userFirstName, userLastName, userLogin, userPass, userAddress, userEmail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public int getIdBylogin(String login) {
        int idUser = 1;
        String selectFromTable = "select * from users " +
                "where id = ?";
        try (PreparedStatement preparedStatement =
                     dbConnection.prepareStatement(selectFromTable)) {
            if (preparedStatement != null) {
                preparedStatement.setString(1, login);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    idUser = resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idUser;
    }

    public void deleteUserById(long id) {
        String deleteFromTableSql = "delete * from users " +
                "where id = ?";
        try (PreparedStatement preparedStatement =
                     dbConnection.prepareStatement(deleteFromTableSql)) {
            if (preparedStatement != null) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(String login, String password) {
        String updateUserIntoTableSql = "UPDATE users SET " +
                "firstName = ?, lastName= ?, login= ?, password= ?, address= ?, email= ? " +
                "WHERE (login = ?)";
        try (PreparedStatement preparedStatement =
                     dbConnection.prepareStatement(updateUserIntoTableSql)) {
            if (preparedStatement != null) {
                preparedStatement.setString(1, getUserByLoginAndPass(login, password).getFirstName());
                preparedStatement.setString(2, getUserByLoginAndPass(login, password).getLastName());
                preparedStatement.setString(3, getUserByLoginAndPass(login, password).getLogin());
                preparedStatement.setString(4, getUserByLoginAndPass(login, password).getAddress());
                preparedStatement.setString(5, getUserByLoginAndPass(login, password).getEmail());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean CheckUserExist(String firstName, String lastName) {
        boolean isDuplicated = false;
        String isUserIntoTableSql = "select * from users " +
                "where firstName=? AND lastName=?";
        try (PreparedStatement preparedStatement =
                     dbConnection.prepareStatement(isUserIntoTableSql)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isDuplicated = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isDuplicated;
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();

        String sql = "SELECT * FROM users";
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long user_Id = resultSet.getLong("id");
                String firstname = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastName");
                String login = resultSet.getString("login");
                String pass = resultSet.getString("pass");
                String address = resultSet.getString("address");
                String email = resultSet.getString("email");
                User user = new User(user_Id, firstname, lastName, login, pass, address, email);
                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }
}
