#Generic web App Boilerplate

A clean , framework-free web application boilerplate built with Java and Javalin

##Prerequisites
-Java 17+
- Gradle
-MySQL Database 

##Project Structure
- `src/main/java/com/example/App.java`: Main backend application and REST setup.
- `src/main/resources/public/`: Static frontend files (HTML, CSS, JS).


## Configuration (MySQL)
1. Copy `.env.example` to `.env`
2. Update the credentials in `.env` with your local MySQL setup.


## How to Build and Run
To build the project:
`gradle build`


To run the application:
`gradle run`

## Health Check
Once the server is running , verify it's working by visiting thee health enpoint:[http://localhost:8080/health](http://localhost:8080/health)


## Future Work
- **Authentication**: Intentionally deferred. Future authentication (JWT/Sessions) should be implemented as Javalin handlers inside the Java backend, with credentials stored via environment variables.