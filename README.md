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

### Endpoints

- POST /users --> create a user
- PUT /users/{displayName} --> update a user by displayName
- GET /users/{displayName} --> get a user by displayName
- POST /restaurants --> create a restaurant
- GET /restaurants/{id} --> get a restaurant by id
- GET /restaurants/search --> get and filter restaurants using query parameters
- POST /reviews --> create a review
- GET /reviews/user/{displayName} --> get reviews by user displayName
- GET /reviews/{id} --> get a review by id
- GET /admin/reviews/pending --> get reviews pending admin approval
- POST /admin/reviews/{id} --> approve a pending review by id