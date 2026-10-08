package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static Connection getConnection() throws SQLException {
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USERNAME");
        String password = System.getenv("DB_PASSWORD");
        
        // Fallback for local development if environment variables are missing
        if (url == null) url = "jdbc:mysql://localhost:3306/my_database";
        if (user == null) user = "root";
        if (password == null) password = "";

        return DriverManager.getConnection(url, user, password);
    }
}
