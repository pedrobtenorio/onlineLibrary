
# OnlineLibrary API

## Overview

OnlineLibrary API is a Java-based application programming interface (API) developed using Spring Boot. It provides functionalities for managing digital libraries, allowing users to interact with the library database, perform CRUD operations, and access various resources.

## Technologies

- **Java:** Version 21
- **Spring Boot:** Version 3.2.5
- **Spring Boot Starter Dependencies:**
  - `spring-boot-starter-data-jpa`
  - `spring-boot-starter-web`
  - `spring-boot-starter-cache`
  - `spring-boot-starter-logging`
  - `spring-boot-starter-actuator`
  - `spring-boot-starter-test`
- **Database:** H2 Database (runtime dependency)
- **Other Dependencies:**
  - `liquibase-core` for database migrations
  - `lombok` for boilerplate code reduction
  
## Project Structure

The OnlineLibrary API project follows a structured organization to enhance maintainability and modularity. The project structure typically consists of several key packages, each serving a specific purpose in the application's architecture:

- **Domain:** This package contains the domain model classes, representing the core entities of the application such as Book, User, Review, etc. These classes encapsulate the data and behavior associated with each entity. Additionally, it includes DTOs (Data Transfer Objects) and enums.

  - **DTO:** DTOs are used to transfer data between different layers of the application, especially between the controller and service layers. They encapsulate only the necessary data for specific operations to improve performance and reduce coupling.

  - **Enums:** Enums represent fixed sets of constants or options. They are used to define attributes with a limited number of possible values, such as book genres or status types.

- **Controller:** Controllers handle incoming HTTP requests and delegate the processing to the appropriate service components. Each controller class defines endpoint mappings and request handling methods.

- **Service:** Service classes contain the business logic of the application. They orchestrate interactions between the controllers and repositories, perform CRUD operations, and implement additional functionalities.

- **Repository:** Repositories provide an abstraction layer for interacting with the underlying database. They typically contain methods for database operations such as fetching, saving, updating, and deleting entities.
- **Utils:** Utility classes may be included in this package to provide reusable functionality or helper methods used across the application.

## Project Structure Overview

```
OnlineLibraryAPI
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── com
│   │   │   │   ├── example
│   │   │   │   │   ├── domain
│   │   │   │   │   │   ├── dto
│   │   │   │   │   │   │   └── (DTO classes)
│   │   │   │   │   │   ├── enums
│   │   │   │   │   │   │   └── (Enum classes)
│   │   │   │   │   │   └── (Domain model classes)
│   │   │   │   │   ├── controller
│   │   │   │   │   │   └── (Controller classes)
│   │   │   │   │   ├── service
│   │   │   │   │   │   └── (Service classes)
│   │   │   │   │   ├── repository
│   │   │   │   │   │   └── (Repository classes)
│   │   │   │   │   ├── utils
│   │   │   │   │   │   └── (Utility classes)
│   │   │   └── resources
│   │   │       ├── db
│   │   │   │   │   │   ├── changelog
│   │   │   │   │   │   └── (migration changelos)
│   │   │       ├── application.properties
│   └── test
│       ├── java
│       │   └── (Test packages and classes)
│       └── resources
│           └── (Test resource files)
└── pom.xml
```

## Setting Up and Running the OnlineLibrary API

To set up and run the OnlineLibrary API project, follow these steps:

### Prerequisites

- Java Development Kit (JDK) version 21 or higher installed on your system.
- Maven installed on your system.

### Steps

1. **Clone the Repository:**

2. **Navigate to the Project Directory:**
   Open your terminal or command prompt and change into the directory where the OnlineLibrary API project is located.

3. **Build the Project:**
   In the terminal or command prompt, execute the following command: `mvn clean install`. This command will build the project and generate the executable JAR file. 
   3. **Run the Application:** After successfully building the project, run the application using the following command: `java -jar target/onlineLibrary-1.0.jar`


## Observability in OnlineLibrary API

### Health Checks

The health of the OnlineLibrary API is monitored using a custom health indicator implemented as a Spring Boot `HealthIndicator`. This indicator checks the status of the database connection and the memory usage of the application.

### Database Health Check

The `CustomHealthIndicator` class contains a method `isDatabaseUp()` that attempts to execute a simple SQL query (`SELECT 1`) using the `JdbcTemplate`. If the query succeeds, the database is considered up; otherwise, it's considered down.

### Memory Usage Health Check

The method `isMemoryUsageHealthy()` calculates the percentage of used heap memory compared to the maximum heap memory available. If the used memory percentage is below 80%, the memory usage is considered healthy.

### Integration with Monitoring Tools

The health endpoint provided by the OnlineLibrary API (`/actuator/health`) can be integrated with monitoring tools like Grafana. Grafana can periodically query this endpoint to retrieve the health status of the application and visualize it on dashboards.

### Future Enhancements

In the future, additional health checks can be added to monitor the status of external dependencies and required APIs. This will provide a more comprehensive view of the application's overall health and facilitate proactive maintenance and troubleshooting.

# Caching in OnlineLibrary API

Caching is implemented at crucial points in the OnlineLibrary API to improve performance and reduce database load. The `BookService` class is annotated with caching annotations to cache frequently accessed data.

## Cache Configurations

- **Cache Names:** The cache names used in the `BookService` class are "books" and "rating".
- **Cache Configuration:** Caching configurations are specified using annotations such as `@CacheConfig`, `@Cacheable`, `@CacheEvict`, and `@Caching`.

## Cached Methods

1. **getAllBooks:** Caches all books retrieved from the database.
2. **getBookById:** Caches individual books by their IDs.
3. **saveBook:** Evicts the cache for all books after saving a new book.
4. **deleteBook:** Evicts the cache for all books and a specific book by ID after deleting a book.
5. **updateBook:** Evicts the cache for all books and a specific book by ID after updating a book.
6. **getTopBooksByAverageRating:** Caches the top 5 books by average rating.
7. **saveReview:** Evicts the cache for all ratings after saving a new review.

## Simulated Delay

The `simulateServerDelay()` method is called in some cached methods to simulate server delay, ensuring that caching behavior is observed effectively.


# Caching Strategy Conclusion

The caching strategy implemented in the OnlineLibrary API was chosen due to the nature of a library system. In such systems, books are not frequently updated or inserted, but the speed of retrieving books is of paramount importance. Additionally, libraries typically house a vast number of books, which can result in slow database access if not optimized.

## Key Considerations

- **Frequent Reads, Infrequent Writes:** Libraries primarily involve reads (i.e., accessing books) rather than frequent writes (inserts or updates). Therefore, caching emphasizes optimizing read operations.
  
- **Performance Optimization:** Caching helps mitigate the latency associated with database queries, ensuring that book retrieval is fast and responsive, even as the library collection grows.
  
- **User Experience:** In a library system, users expect fast access to book information. Caching enhances user experience by reducing response times and providing a seamless browsing experience.

## Benefits

- **Improved Responsiveness:** Caching ensures that book retrieval operations are fast, even during peak usage periods, enhancing the responsiveness of the application.
  
- **Reduced Database Load:** By caching frequently accessed data, the API reduces the number of database queries, thereby lowering the overall load on the database server.
  
- **Scalability:** The caching strategy enables the API to scale effectively, accommodating a growing number of users and books without sacrificing performance.

# Database Modeling

## Entity-Relationship (ER) Diagram

![ER Diagram](https://i.imgur.com/y9euA3y.png)

<!-- Replace the URL above with the actual link to your ER diagram image -->

## Description

The database modeling for the OnlineLibrary API is designed to efficiently store and manage information about books, users, reviews, and book copies. The ER diagram provides a visual representation of the database schema, illustrating the relationships between different entities and their attributes.

## Book

- **Attributes:**
  - id: Unique identifier for the book.
  - title: Title of the book.
  - author: Author of the book.
  - genre: Genre of the book.
  - synopsis: Summary of the book.

- **Description:**
  Represents a book in the library system. Each book has a unique identifier (`id`) and contains information such as its title, author, genre, and synopsis.

## BookCopy

- **Attributes:**
  - id: Unique identifier for the book copy.
  - book: Reference to the associated book.
  - status: Status of the book copy (e.g., available, reserved, borrowed).

- **Description:**
  Represents a physical copy of a book available in the library. Each book copy is associated with a specific book and indicates its availability status.

## Reservation

- **Attributes:**
  - id: Unique identifier for the reservation.
  - user: Reference to the user making the reservation.
  - bookCopy: Reference to the reserved book copy.
  - reservationDate: Date when the reservation was made.
  - dueDate: Expected return date for the reserved book copy.
  - active: Indicates whether the reservation is active or fulfilled.

- **Description:**
  Represents a reservation made by a user to borrow a book copy from the library. It contains details such as the user making the reservation, the reserved book copy, and reservation dates.

## Review

- **Attributes:**
  - id: Unique identifier for the review.
  - user: Reference to the user who submitted the review.
  - book: Reference to the reviewed book.
  - rating: Rating given by the user for the book.
  - comment: Additional comments or feedback provided by the user.

- **Description:**
  Represents a review submitted by a user for a particular book in the library. It includes details such as the user, reviewed book, rating, and optional comments.

## User

- **Attributes:**
  - id: Unique identifier for the user.
  - username: Username of the user.
  - email: Email address of the user.

- **Description:**
  Represents a user of the library system. Each user is identified by a username and email address and interacts with the system by making reservations, submitting reviews, etc.



## Relationships

### Book - BookCopy
- **Description:**
  One book can have multiple copies (e.g., different editions or physical copies). This relationship represents a One-to-Many association from Book to BookCopy, where each Book has multiple BookCopies.

### BookCopy - Reservation
- **Description:**
  Each book copy can be reserved by multiple users at different times. This relationship represents a One-to-Many association from BookCopy to Reservation, where each BookCopy can have multiple Reservations.

### User - Reservation
- **Description:**
  Each user can make multiple reservations for different book copies. This relationship represents a One-to-Many association from User to Reservation, where each User can have multiple Reservations.

### User - Review
- **Description:**
  Each user can write multiple reviews for different books. This relationship represents a One-to-Many association from User to Review, where each User can have multiple Reviews.

### Book - Review
- **Description:**
  Each book can have multiple reviews written by different users. This relationship represents a One-to-Many association from Book to Review, where each Book can have multiple Reviews.

# Migrations

Liquibase is an open-source library for tracking, managing, and applying database schema changes. It allows developers to define database changes in a declarative manner using XML, YAML, JSON, or SQL formats. Liquibase ensures that all database schema changes are version-controlled, allowing for easy management and collaboration among development teams.

### Database ChangeLog

the project contains a `databaseChangeLog.xml` file, which serves as the main entry point for Liquibase. This file defines a sequence of changesets that represent the evolution of your database schema over time.

## Curent system changeLog

```xml
<databaseChangeLog>
  <include file="db/changelog/0000_initial_schema.sql"/>
  <include file="db/changelog/0001_populate_initial_db.sql"/>
  <include file="db/changelog/0003_insert_more_books.sql"/>
</databaseChangeLog>
```


