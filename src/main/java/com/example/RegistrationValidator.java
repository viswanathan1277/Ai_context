package com.example;

public class RegistrationValidator {
    
    // Validates registration fields and returns an error message if invalid, or null if valid.
    public static String validate(String name, String phone, String email, String password) {
        if (name == null || name.trim().isEmpty()) {
            return "Name cannot be empty";
        }
        if (phone == null || phone.trim().isEmpty()) {
            return "Phone cannot be empty";
        }
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            return "Invalid email format";
        }
        if (password == null || password.trim().isEmpty()) {
            return "Password cannot be empty";
        }
        return null; // No errors
    }
}
