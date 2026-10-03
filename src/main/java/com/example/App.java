package com.example;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class App{
    public static void main (String [] args){
        Javalin app = Javalin.create(config->{
            // server frountend static file from resouce 

            config.staticFiles.add("/public",Location.CLASSPATH);
        }).start(8080);
        // basic static endpoint

        app.get("/health",ctx ->{
        ctx.status(200);
        ctx.result("OK");
         }); 

        System.out.println("server started !!!!!");
        System.out.println("Health endpoint: http://localhost:8080/health");

        }
    }
