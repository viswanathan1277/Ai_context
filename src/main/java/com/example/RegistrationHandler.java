package com.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

public class RegistrationHandler implements HttpHandler {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // 1. Only allow POST requests
        if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            sendResponse(exchange, 405, "Method Not Allowed");
            return;
        }

        try {
            // 2. Read JSON from request body
            InputStream is = exchange.getRequestBody();
            JsonNode body = mapper.readTree(is);
            
            String name = body.has("name") ? body.get("name").asText() : null;
            String phone = body.has("phone") ? body.get("phone").asText() : null;
            String email = body.has("email") ? body.get("email").asText() : null;
            String password = body.has("password") ? body.get("password").asText() : null;

            // 3. Validate using our TDD-built validator
            String validationError = RegistrationValidator.validate(name, phone, email, password);
            if (validationError != null) {
                sendResponse(exchange, 400, validationError);
                return;
            }

            // 4. Hash Password using our TDD-built utility
            String hashedPassword = PasswordUtils.hash(password);

            // 5. Save to Database using our repository
            boolean success = UserRepository.saveUser(name, phone, email, hashedPassword);
            
            if (success) {
                sendResponse(exchange, 201, "Registration successful");
            } else {
                sendResponse(exchange, 409, "Email already exists");
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            sendResponse(exchange, 500, "Internal Server Error");
        } catch (Exception e) {
            sendResponse(exchange, 400, "Invalid request format");
        }
    }

    // Helper method to send text responses back to the browser
    private void sendResponse(HttpExchange exchange, int statusCode, String message) throws IOException {
        exchange.sendResponseHeaders(statusCode, message.length());
        OutputStream os = exchange.getResponseBody();
        os.write(message.getBytes());
        os.close();
    }
}
