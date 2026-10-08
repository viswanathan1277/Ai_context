package com.example;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {
    
    // Scrambles (hashes) a plaintext password
    public static String hash(String plaintext) {
        return BCrypt.hashpw(plaintext, BCrypt.gensalt(10));
    }
    
    // Checks if a plaintext password matches the scrambled hash
    public static boolean check(String plaintext, String hashed) {
        return BCrypt.checkpw(plaintext, hashed);
    }
}
