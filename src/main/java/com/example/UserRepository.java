package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class UserRepository {
    
    // Saves a new user to MySQL. Returns true on success, false if the email already exists.
    public static boolean saveUser(String name, String phone, String email, String passwordHash) throws SQLException {
        String sql = "INSERT INTO users (name, phone, email, password) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, name);
            stmt.setString(2, phone);
            stmt.setString(3, email);
            stmt.setString(4, passwordHash);
            
            stmt.executeUpdate();
            return true;
            
        } catch (SQLIntegrityConstraintViolationException e) {
            // This happens if the MySQL UNIQUE constraint on the email column fails
            return false;
        }
    }
}
