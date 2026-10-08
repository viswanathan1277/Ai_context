package com.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class App {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Health endpoint
        server.createContext("/health", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = "OK";
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });

        // Static file handler
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String path = exchange.getRequestURI().getPath();
                if (path.equals("/")) {
                    path = "/index.html";
                }
                
                InputStream is = getClass().getResourceAsStream("/public" + path);
                if (is == null) {
                    exchange.sendResponseHeaders(404, -1);
                    return;
                }

                // Basic content typing
                if (path.endsWith(".css")) exchange.getResponseHeaders().set("Content-Type", "text/css");
                else if (path.endsWith(".js")) exchange.getResponseHeaders().set("Content-Type", "application/javascript");
                else if (path.endsWith(".html")) exchange.getResponseHeaders().set("Content-Type", "text/html");

                exchange.sendResponseHeaders(200, 0);
                OutputStream os = exchange.getResponseBody();
                is.transferTo(os);
                os.close();
                is.close();
            }
        });
 // Registration endpoint
        server.createContext("/register", new RegistrationHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Standard Java HttpServer started!");
        System.out.println("Health endpoint: http://localhost:8080/health");
    }
}
       
