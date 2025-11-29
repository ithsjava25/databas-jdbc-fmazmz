package com.example.jdbc;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class ConnectionManager {

    private static Connection connection;

    public static void init(String jdbcUrl, String username, String password) {
        if (connection != null) {
            return;
        }
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to establish DB connection.", e);
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            throw new IllegalStateException("ConnectionManager not initialized.");
        }
        return connection;
    }
}
