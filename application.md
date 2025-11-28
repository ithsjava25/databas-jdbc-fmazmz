## Java JDBC Springboot Application
This project is a simple console based CRUD application that uses Spring Boot and JPA for database access.  
It supports login, listing moon missions, creating accounts, updating passwords, and deleting accounts.

### Dependencies:
- Spring Boot V4.0.0
- spring-boot-starter
- spring-boot-starter-data-jpa
- postgresql


### Application Features
- Login with username and password
- List moon missions
- Get mission by ID
- Count missions by year
- Create user accounts
- Update password of the logged in account
- Delete accounts


### How to Run
The application reads database credentials from system properties.

#### Option 1 (Postgres Docker Image)
If you prefer a consistent reproducible database environment, start PostgreSQL via Docker Compose:
```bash
docker compose up -d
```
Then run:
```bash
java \
-DAPP_JDBC_URL=jdbc:postgresql://localhost:5432/moondb \
-DAPP_DB_USER=postgres \
-DAPP_DB_PASS=postgres \
-jar target/jdbc-1.0-SNAPSHOT.jar
```


#### Option 2 (Use your own Postgres docker container)
```bash
java \
  -DAPP_JDBC_URL=jdbc:postgresql://<host>:<port>/<database> \
  -DAPP_DB_USER=<your_user> \
  -DAPP_DB_PASS=<your_pass> \
  -jar target/jdbc-1.0-SNAPSHOT.jar
```
Replace <host>, <port>, <database>, <your_user>, and <your_pass> with your own values.


#### Option 3 (Development Mode)
This mode automatically launches a temporary MySQL container with seeded data using 'init.sql'.
Let the application start a MySQL container for you and allow you to use the application.
Enable Dev Mode with one of the following:

- VM option: 
  - ```bash 
    java -DdevMode=true -jar target/jdbc-1.0-SNAPSHOT.jar
    ```
- Environment Variable:
  - ```bash
    DEV_MODE=true java -jar target/jdbc-1.0-SNAPSHOT.jar
    ```
- CLI Arg:
  - ```bash
    java -jar target/jdbc-1.0-SNAPSHOT.jar --dev
    ```