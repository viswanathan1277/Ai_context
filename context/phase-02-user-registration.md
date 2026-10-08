# Phase 02 — User Registration

## 1. Phase title and purpose
**Phase 02 — User Registration**

The purpose of this phase was to implement the first real feature: a simple, educational, and framework-free user registration flow. It accepts Name, Phone, Email, and Password, sending the data from the browser to the Java backend and saving it in MySQL using direct JDBC. A strict requirement was to avoid backend frameworks (like Javalin) and ORMs (like Hibernate).

---

## 2. Starting state
- **Initial repository condition:** The repository contained the Phase 01 boilerplate using the Javalin framework.
- **Technology constraints:** 
  - Backend: Java standard `HttpServer` (migrated from Javalin), Gradle.
  - Frontend: Vanilla HTML, CSS, JavaScript.
  - Database: MySQL, direct JDBC.
- **Initial assumptions:** The project needed to be migrated to Java's built-in `HttpServer` first to satisfy the framework-free constraint before building registration logic.

---

## 3. Implementation history
### 1. Migrating to standard Java HttpServer (Refactoring)
- Removed `javalin` dependency from `build.gradle`.
- Rewrote `App.java` to use `com.sun.net.httpserver.HttpServer`.
- Implemented a manual static file handler in `App.java` to serve the HTML/CSS/JS files from the `/public` classpath directory.

### 2. Database connection & Users table setup
- Added SQL schema for a `users` table with a `UNIQUE` constraint on the `email` column.
- Created `Database.java` to establish a JDBC connection using environment variables (`DB_URL`, `DB_USERNAME`, `DB_PASSWORD`), with fallbacks for local XAMPP development.

### 3. TDD: Password hashing & BCrypt dependency
- Added `jbcrypt` (for password hashing) and `junit-jupiter` (for TDD) dependencies to `build.gradle`.
- Created `PasswordUtilsTest.java` (RED step).
- Created `PasswordUtils.java` implementing BCrypt hashing (GREEN step).

### 4. TDD: Registration validation & User repository
- Created `RegistrationValidatorTest.java` to verify rejection of empty fields and invalid emails (RED step).
- Created `RegistrationValidator.java` with static validation logic (GREEN step).
- Created `UserRepository.java` using a `PreparedStatement` to safely insert users and handle duplicate email constraints.

### 5. Registration HTTP Handler
- Created `RegistrationHandler.java` mapping to the `/register` endpoint.
- Used Jackson `ObjectMapper` to parse the incoming JSON payload.
- Integrated the validator, password hasher, and repository into a clean HTTP flow returning standard HTTP status codes (`201`, `400`, `409`, `500`).

### 6. Frontend HTML & Vanilla JS
- Modified `index.html` to include the semantic registration form.
- Modified `main.js` to intercept the form submission, prevent default browser refresh, and use the `fetch` API to send a JSON `POST` request to the backend, rendering success/error messages based on the HTTP status code.

---

## 4. Failures and fixes
- **Build configuration wiped:** The user accidentally deleted the entire `plugins` and `application` block from `build.gradle` when removing Javalin. Provided the full corrected file to restore compilation.
- **Test file mix-up:** The user mistakenly placed the `PasswordUtils` production code inside the `src/test` folder and the test code in the `src/main` folder. Instructed the user to cleanly swap the files to adhere to standard Maven/Gradle directory structures.
- **Wrong folder for Repository:** The user placed `UserRepository.java` in the test folder. Instructed them to move it to `src/main`.
- **Database missing:** The user encountered a `500 Internal Server Error` during the first registration attempt because they skipped the MySQL setup. Directed them to install XAMPP and create the database and table via phpMyAdmin.
- **Locked Build Folder:** The user encountered a `Failed to clean up stale outputs` error during `gradle clean` due to an orphaned Java process/locked directory on Windows. Bypassed by renaming the `build` directory to `build_locked`.

---

## 5. Architectural decisions
- **Strictly Framework-Free:** Replaced Javalin with Java's standard `com.sun.net.httpserver.HttpServer`.
- **Vanilla Frontend:** Used raw HTML forms and `fetch` in JavaScript, avoiding React/Angular.
- **Direct JDBC:** Used `java.sql.PreparedStatement` to securely query MySQL without an ORM.
- **Test-Driven Development:** Utilized strict test-first development for validation and password hashing logic to ensure educational clarity.

---

## 6. Verification
- **Success:** The application correctly connects to MySQL, hashes the password via BCrypt, and returns `201 Created` with a success message in the browser.
- **Duplicate handling:** Evaluates database constraints and returns `409 Conflict`.
- **Static file serving:** `index.html` and `main.js` correctly served by the custom `HttpServer` handler.

---

## 7. Deferred work
- Login functionality.
- Sessions and authorization.
- Advanced phone number format validation.
