# DiningReviewAPI

DiningReviewAPI is a simple Spring Boot RESTful API for managing restaurants and reviews. It uses **Spring Boot**, **Spring Data JPA**, and **H2 database** for quick development and testing.

---

## Getting Started

These instructions will help you run the DiningReviewAPI locally.

---

### Requirements

- Java 25
- Maven
- IDE (IntelliJ IDEA recommended)
- Optional: Postman or curl for testing endpoints

---

### Running the Application

1. Clone the repository:
   git clone https://github.com/<your-username>/DiningReviewAPI.git
cd DiningReviewAPI

3. Build the project with Maven:
   mvn clean install

4. Run the Spring Boot application:
   mvn spring-boot:run

### Database

This project uses H2 Database for persistence. The database file is stored locally as:
./data/diningdb

The application.properties configuration:
spring.application.name=DiningReviewAPI
spring.datasource.url=jdbc:h2:file:./data/diningdb
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.hibernate.ddl-auto=create-drop

Note: create-drop will recreate the database every time the app starts.
