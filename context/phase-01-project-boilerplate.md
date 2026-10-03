# Phase 01 — Project Boilerplate

## 1. Phase title and purpose
**Phase 01 — Project Boilerplate**

The purpose of this phase was to establish an initial, clean, framework-free web application boilerplate using Java (Javalin) and Vanilla web technologies. The intent was to prepare a minimal foundation suitable for future REST APIs and features without implementing authentication or business logic yet.

---

## 2. Starting state
- **Initial repository condition:** The repository started as a completely empty directory.
- **Technology constraints:** 
  - Backend: Java, Gradle build tool.
  - Frontend: Vanilla HTML, CSS, JavaScript (No React, Angular, or Tailwind).
  - Database: MySQL (prepared via configuration only).
- **Initial assumptions:** The project requires a lightweight, scalable base that defers authentication.

---

## 3. Final outcome
The initial boilerplate has been successfully established.
- A Javalin backend was configured to serve static assets and a `/health` REST endpoint.
- A vanilla HTML/CSS/JS frontend was created.
- MySQL configuration was established via `.env.example`.
- **Intentionally NOT implemented:** Authentication, Registration, Login, password hashing, JWT, user tables, and business features.

---

## 4. Chronological implementation journey
1. The project started empty.
2. The Gradle build and settings files were created (`build.gradle`, `settings.gradle`) targeting Java 17.
3. Javalin and MySQL Connector/J were selected and added as dependencies.
4. An entry point class (`app.java` - historically named with lowercase `a`) was created with Javalin configured to serve `/public` on the classpath.
5. The `/health` endpoint was added.
6. **Mistake & Fix:** The user encountered Java syntax errors (missing quotes and semicolons in `app.java`), which were corrected iteratively. A typo in `build.gradle` (`sourceCompatibality`) was also identified and fixed.
7. Frontend files (`index.html`, `style.css`, `main.js`) were created.
8. **Mistake & Fix:** The user initially placed `main.js` in a nested folder `css/js/main.js`. This was corrected by moving it to `src/main/resources/public/js/main.js`.
9. The `.env.example` file was created to hold placeholder database configuration.
10. The `.gitignore` and `README.md` files were created.
11. **Mistake & Fix:** The user attempted to run `gradle build` but Gradle was not installed on their system path. After instructions, the user successfully installed Gradle, verified it via `gradle -v`, and successfully ran `gradle build`.

---

## 5. Files created or modified

| File Path | Purpose | Status | Implementation Details |
| --- | --- | --- | --- |
| `build.gradle` | Build configuration | Created | Added `io.javalin:javalin:6.1.3`, `slf4j-simple`, `jackson-databind`, and `mysql-connector-j`. Configured for Java 17. |
| `settings.gradle` | Project settings | Created | Sets root project name to `generic-web-app`. |
| `src/main/java/com/example/app.java` | Main application | Created | Note: Filename is currently `app.java` (lowercase 'a') despite holding `public class App`. Starts Javalin on port 8080, serves static files, and exposes `/health`. |
| `src/main/resources/public/index.html` | Frontend entry | Created | Vanilla HTML linking to CSS and JS. |
| `src/main/resources/public/css/style.css` | Frontend styling | Created | Minimal vanilla CSS. |
| `src/main/resources/public/js/main.js` | Frontend logic | Created | Basic alert tied to a button click. |
| `.env.example` | DB Config template | Created | Contains `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`. |
| `.gitignore` | Ignore rules | Created | Ignores `.gradle/`, `build/`, `.idea/`, `.vscode/`, and `.env`. |
| `README.md` | Project docs | Created | Documents setup, build, and run instructions. |

---

## 6. Architecture
```text
Browser
   ↓
Javalin (Port 8080)
   ├── Static frontend (/public)
   └── REST endpoints (/health)
```
**Why Javalin?** It was selected over heavier frameworks (like Spring Boot) because it provides a lightweight, transparent API that natively supports both REST endpoints and static file serving without "magic" annotations, perfectly matching the requirement for a "simple" and "reasonable" backend approach.

---

## 7. Frontend implementation
- **HTML location:** `src/main/resources/public/index.html`
- **CSS location:** `src/main/resources/public/css/style.css`
- **JS location:** `src/main/resources/public/js/main.js`
- **Serving:** Javalin uses `config.staticFiles.add("/public", Location.CLASSPATH);` to serve these files.
- **Behavior:** The frontend is completely framework-free. The index page loads the stylesheet and script, and contains a single button that triggers a JavaScript alert when clicked to prove JS is loading.

---

## 8. Backend implementation
- **Java version:** Java 17
- **Main class:** `com.example.App` (inside `app.java`)
- **Javalin version:** 6.1.3
- **Server port:** 8080
- **Static file configuration:** Serves `/public` from the classpath.
- **`/health` endpoint:** Returns HTTP 200 with "OK".

---

## 9. Gradle configuration
- **Plugins:** `java`, `application`
- **Java version:** 17 (`sourceCompatibility` and `targetCompatibility`)
- **Main Class:** `com.example.App`
- **Dependencies:** 
  - `io.javalin:javalin:6.1.3`
  - `org.slf4j:slf4j-simple:2.0.12`
  - `com.fasterxml.jackson.core:jackson-databind:2.17.0`
  - `com.mysql:mysql-connector-j:8.3.0`
- **Build command:** `gradle build`
- **Run command:** `gradle run`

---

## 10. MySQL configuration
- **JDBC Dependency:** `mysql-connector-j:8.3.0` is installed via Gradle.
- **Configuration Template:** `.env.example` was created.
- **Keys:** `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`.
- **Status:** Prepared at the configuration level only. **No actual JDBC connection logic or driver initialization exists in the Java code yet.**

---

## 11. Environment/configuration
Configuration currently relies on `.env.example` containing placeholder connection variables:
```text
DB_URL=jdbc:mysql://localhost:3306/my_database
DB_USERNAME=db=DB_USERNAME
DB_PASSWORD=secret_password
```
- **Committed:** `.env.example` is committed (or intended to be).
- **Ignored:** `.env` is listed in `.gitignore` to prevent secret leakage.
- **Usage:** Developers are expected to copy `.env.example` to `.env` and fill in local values. The Java code has not yet been implemented to read this.

---

## 12. Verification
Historical verification that was completed:
- `gradle -v` (Confirmed Gradle 9.7.1)
- `gradle build` (BUILD SUCCESSFUL in 21s)

*Note: While `gradle run` was executed and ran successfully based on terminal metadata, explicit browser verification of the endpoints by the user is assumed successful but wasn't explicitly pasted.*

---

## 13. Failures and fixes
| Problem | Cause | Fix | Final Status |
| --- | --- | --- | --- |
| Java compilation typo | `sourceCompatibality` instead of `sourceCompatibility` in `build.gradle` | Corrected the typo. | Resolved. |
| Java syntax errors | Missing quotes, missing semicolons, extra brackets in `app.java` | User iteratively applied fixes provided during guided implementation. | Resolved. |
| Incorrect asset paths | User placed `main.js` at `src/main/resources/public/css/js/main.js` | User moved it to `src/main/resources/public/js/main.js` | Resolved. |
| Gradle command unavailable | Gradle was not installed/in PATH on the Windows machine | Provided instructions to install Gradle locally; user successfully added to PATH. | Resolved. |

---

## 14. Architecture/design decisions
- **Vanilla Frontend:** No React, Angular, Vue, or Tailwind.
- **Javalin:** Selected for its minimal overhead compared to Spring Boot.
- **Database configuration:** Use environment variables instead of hardcoded strings to ensure security best practices from day one.
- **Authentication intentionally deferred:** The system was kept bare-bones intentionally.

---

## 15. Security considerations
- Real DB credentials are NOT committed.
- `.env` is actively ignored in `.gitignore`.
- *Missing:* Authentication, password handling, user tables, and authorization do not exist yet.

---

## 16. Deferred work
The following were strictly excluded from this phase and are deferred for future implementation:
- Registration, Login
- Password hashing
- Sessions, JWT, OAuth
- User roles and profiles
- User database tables
- Real JDBC connection instantiation

---

## 17. Unresolved issues / limitations
- **Contradiction/Issue:** The main application file is physically named `app.java` (lowercase 'a') but defines `public class App`. The Java compiler on Windows is case-insensitive regarding file systems, so it compiled successfully, but this violates Java naming conventions and will break on Linux/Mac.

---

## 18. Git / commit linkage
- **Status:** The repository is **not** currently a Git repository (`git status` returns `fatal: not a git repository`). 
- **Commit:** Phase 01 is not committed yet.

---

## 19. Resume From Here
The repository contains a successfully compiling Javalin server serving a static vanilla frontend and a `/health` endpoint.

**Next Feature:** User Registration

Expected future flow:
```text
Browser
  ↓
Registration HTML
  ↓
Vanilla JavaScript
  ↓
HTTP POST
  ↓
Java/Javalin
  ↓
JDBC
  ↓
MySQL
```
*Note for the next developer/agent:* When resuming, begin by implementing the Registration feature. You may want to start by renaming `app.java` to `App.java` for correctness before writing new handlers. The JDBC connection logic will need to be written to read from the environment variables.
