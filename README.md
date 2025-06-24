# 📓 JournalApp

A secure and modular Spring Boot-based RESTful API for managing personal journal entries. Includes user authentication, role-based access control, and a layered architecture for clean code management.

---

## 🚀 Features

- User registration and login with JWT-based authentication  
- Role-based authorization (Admin, User)  
- CRUD operations for journal entries  
- Health check endpoint for service monitoring  
- Modular service-repository-controller architecture  

---

## 🛠 Tech Stack

- **Backend:** Java 17, Spring Boot, Spring Security, Spring Data JPA  
- **Database:** MongoDB  
- **Authentication:** JWT (JSON Web Tokens)  
- **Build Tool:** Maven  
- **Version Control:** Git + GitHub  

---

## 📁 Folder Structure

```
src/
├── main/
│   ├── java/com/vamsi/journalApp/
│   │   ├── controller/            # REST controllers
│   │   ├── entity/                # Data models
│   │   ├── repository/            # JPA repositories
│   │   ├── service/               # Business logic
│   │   └── JournalApplication.java# Entry point
│   └── resources/                 # Configuration files
├── test/                         # Unit and integration tests
```

---

## 🧪 How to Run Locally

```bash
# Clone the repository
git clone https://github.com/mvk24199/journalApp.git
cd journalApp

# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
```

The app will start on `http://localhost:8080`

---

## 🔌 API Overview

| Method | Endpoint                         | Description                         | Auth Required |
|--------|----------------------------------|-------------------------------------|---------------|
| GET    | `/health`                        | Health check                        | ❌            |
| POST   | `/user/register`                 | Register new user                   | ❌            |
| POST   | `/user/login`                    | Authenticate and receive JWT        | ❌            |
| GET    | `/journal`                       | Get all journal entries             | ✅            |
| POST   | `/journal`                       | Create a journal entry              | ✅            |
| PUT    | `/journal/{id}`                  | Update an existing entry            | ✅            |
| DELETE | `/journal/{id}`                  | Delete a journal entry              | ✅            |

---

## 🔮 Future Improvements

- Swagger/OpenAPI integration for API documentation  
- Docker containerization for deployment  
- Role management admin panel  
- Tag-based journal filtering  

---

## 📜 License

This project is licensed under the MIT License.

---

## 👨‍💻 Author

**Vamsi Krishna Madavarapu**  
📍 Seattle, WA  
📧 madavarapu.vamsikrishna@gmail.com  
🔗 [GitHub](https://github.com/mvk24199) • [LinkedIn](https://linkedin.com/in/vamsi-krishna-madavarapu)
