package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RegistrationValidatorTest {

    @Test
    public void testRejectsEmptyName() {
        String error = RegistrationValidator.validate("", "1234567890", "test@test.com", "password123");
        assertEquals("Name cannot be empty", error);
    }

    @Test
    public void testRejectsInvalidEmail() {
        String error = RegistrationValidator.validate("John", "1234567890", "bademail", "password123");
        assertEquals("Invalid email format", error);
    }

    @Test
    public void testAcceptsValidInput() {
        String error = RegistrationValidator.validate("John", "1234567890", "test@test.com", "password123");
        assertNull(error);
    }
}
