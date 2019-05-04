package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnector {
    private final static String dbUrl = "jdbc:mysql://localhost:3307/matecrud?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final static String name = "root";
    private final static String pass = "root";

    public static Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(dbUrl, name, pass);
            return connection;
        } catch (Exception es) {
            es.printStackTrace();
        }
        return null;
    }
}
