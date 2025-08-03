# 📝 Notes App with JWT Authentication

A simple RESTful Notes application built using **Spring Boot**, featuring **JWT-based authentication** and **role-based access control**. Users can sign up, log in, and perform CRUD operations on their notes securely.

## 🚀 Features

- User Signup & Login with JWT
- Password encryption using `BCryptPasswordEncoder`
- Role-based access (`USER` role by default)
- Create, Read, Update, Delete (CRUD) operations on notes
- Authenticated endpoints secured with JWT
- In-memory H2 database for development
- H2 console access for quick DB inspection
- Stateless authentication with Spring Security

## 📦 Tech Stack

- Java 17
- Spring Boot 3.x
- Spring Security
- JWT (JSON Web Tokens)
- H2 In-Memory Database
- Maven

## 📂 Project Structure

src/
└── main/
├── java/
│ └── com.notes_app_with_jwt.NotesApp/
│ ├── controller/
│ ├── dto/
│ ├── model/
│ ├── repository/
│ ├── security/
│ └── service/
└── resources/
├── application.properties
└── schema.sql / data.sql (if needed)


## 🛠️ Getting Started

### Prerequisites
- Java 17+
- Maven
- IDE (IntelliJ IDEA recommended)

### Run the App

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run

```
App will start at: http://localhost:8080

H2 Console
- Visit: http://localhost:8080/h2-console

- JDBC URL: jdbc:h2:mem:testdb

- Username: sa

- Password: (leave blank)

### 🔐 Authentication & Authorization

#### Public Endpoints
| Method | Endpoint        | Description         |
| ------ | --------------- | ------------------- |
| POST   | `/notes/signup` | Register a new user |
| POST   | `/notes/login`  | Login & receive JWT |


#### Secured Endpoints
| Method | Endpoint      | Description          |
| ------ | ------------- | -------------------- |
| GET    | `/notes`      | Get all user notes   |
| GET    | `/notes/{id}` | Get a specific note  |
| POST   | `/notes`      | Create a new note    |
| PUT    | `/notes/{id}` | Update existing note |
| DELETE | `/notes/{id}` | Delete a note        |

⚠️ All secured endpoints require a valid JWT token in the Authorization header:
Authorization: Bearer <token>

### 📬 Sample API Requests
#### Signup
```
POST /notes/signup
Content-Type: application/json

{
"username": "john_doe",
"password": "password123",
"email": "john@example.com"
}
```

#### Login
```
POST /notes/login
Content-Type: application/json

{
"username": "john_doe",
"password": "password123"
}


Response:
{
"token": "eyJhbGciOiJIUzI1NiIsInR..."
}
```



#### Get All Notes
```
GET /notes
Authorization: Bearer <token>
```

🧪 TODOs / Enhancements
- Add role ADMIN and additional endpoints

- Add Swagger/OpenAPI docs

- Dockerize the application

- Replace H2 with MySQL/PostgreSQL for production

- Add unit and integration tests

