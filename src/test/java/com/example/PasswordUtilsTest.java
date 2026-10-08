package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordUtilsTest {

    @Test
    public void testHashPasswordReturnsHashedString() {
        String plaintext = "mySecretPassword123";
        String hashed = PasswordUtils.hash(plaintext);
        
        assertNotNull(hashed);
        assertNotEquals(plaintext, hashed);
        assertTrue(hashed.startsWith("$2a$"));
    }

    @Test
    public void testCheckPasswordReturnsTrueForMatch() {
        String plaintext = "anotherSecret";
        String hashed = PasswordUtils.hash(plaintext);
        
        assertTrue(PasswordUtils.check(plaintext, hashed));
    }
}
