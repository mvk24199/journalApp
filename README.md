# 📝 JournalApp

A secure and role-based journal management REST API that allows users to create, view, and manage personal journal entries. Built with Spring Boot and MongoDB, this application supports role-based access (Admin, User, Public), JWT authentication, and RESTful endpoints.

---

## 🚀 Features

- ✍️ Create, update, view, and delete journal entries
- 🔐 Role-based access (Admin, User, Public)
- 🔑 Secure login and JWT-based authentication
- 👤 Context-aware APIs using `CurrentUserProvider`
- 📡 Health check and public endpoint exposure
- ✅ Unit and integration tests using JUnit
- 📁 Clean separation of layers: Controller, Service, Repository

---

## 🧰 Tech Stack

- **Backend:** Java 17, Spring Boot
- **Security:** Spring Security, JWT
- **Database:** MongoDB
- **Build Tool:** Maven
- **Testing:** JUnit 5
- **Version Control:** Git, GitHub

---

## 📂 Folder Structure

```
journalApp/
├── src/
│   ├── main/
│   │   ├── java/com/vamsi/journalApp/
│   │   │   ├── controller/         # REST Controllers (Admin, User, Public)
│   │   │   ├── config/             # Security Configurations
│   │   │   ├── service/            # Business Logic
│   │   │   ├── repository/         # MongoDB Repositories
│   │   │   └── util/               # Utility - CurrentUserProvider
│   │   └── resources/
│   │       └── application.yml     # App configuration
│   └── test/
│       └── java/com/vamsi/journalApp/
│           └── service/            # Unit Tests
└── pom.xml                         # Maven config
```

---

## ⚙️ How to Run Locally

1. **Clone the repository**
   ```bash
   git clone https://github.com/mvk24199/journalApp.git
   cd journalApp
   ```

2. **Start MongoDB** (locally or via Docker)

3. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Access API**
   - `http://localhost:8080/api/public` - Public access
   - `http://localhost:8080/api/user` - JWT-authenticated user routes
   - `http://localhost:8080/api/admin` - Admin-only access

---

## 🔌 API Overview

| Endpoint                     | Method | Role    | Description                      |
|-----------------------------|--------|---------|----------------------------------|
| `/api/public/health`        | GET    | Public  | Health check endpoint            |
| `/api/user/journals`        | GET    | User    | Get all user journal entries     |
| `/api/user/journals/{id}`   | DELETE | User    | Delete a journal entry           |
| `/api/admin/users`          | GET    | Admin   | List all registered users        |

> 🔐 Auth is handled via JWT. Login endpoint returns token to be included in `Authorization` header as `Bearer <token>`.

---

## 🌱 Future Improvements

- Integrate Swagger/OpenAPI for live API docs
- Add MongoDB schema validation
- Implement email/password reset flow
- Build frontend dashboard with React/Vue

---

## 📜 License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).

---

## 👨‍💻 Author

**Vamsi Krishna Madavarapu**  
📧 [madavarapu.vamsikrishna@gmail.com](mailto:madavarapu.vamsikrishna@gmail.com)  
🔗 [LinkedIn](https://linkedin.com/in/vamsi-krishna-madavarapu)  
🔗 [GitHub](https://github.com/mvk24199)

---

> ⭐ Don’t forget to star the repo if you found it helpful!
