package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {

    private static final String URL = "jdbc:postgresql://localhost:5432/company";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    private Connection connection;


    public void connect() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Successful connection!");
        } catch (SQLException e) {
            System.out.println("Error connection!");
            e.printStackTrace();
        }
    }


    public Connection getConnection() {
        return connection;
    }


    public void executeQuery(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Request was completed successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection was closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
