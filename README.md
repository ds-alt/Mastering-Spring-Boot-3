# Mastering Spring Boot 3 - Book Management Application
This is a learning project based on the book **Mastering Spring Boot 3**, focused on building a full-stack application using Spring Boot (Java backend) and Thymeleaf (HTML templates) for book management. It demonstrates:

- CRUD operations
- DTO and Mapper layers
- Asynchronous service
- Logging system with download support
- Admin dashboard and login
- REST API and HTML frontend
- Deployment with or without Docker

---

## ▶️ How to Run

### ✅ Run Without Docker

Make sure MySQL is installed and running locally:

1. Create a MySQL database named: `mastering_spring_boot_3`  
2. Set credentials in `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mastering_spring_boot_3?useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=admin
spring.datasource.password=admin
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### Then run:
```
mvn clean install
mvn spring-boot:run
```
### 🐳 Run With Docker
You must have Docker and Docker Compose installed.
Use the provided docker-compose.yml and .env file.

Run:
```
docker-compose up --build
```
The app will be available at: http://localhost:8080

### 🔌 API Endpoints
### Public Endpoints
Method	Endpoint	Description
```
GET	/hello	         Static greeting
GET	/config	         Message from application.properties
GET	/greet?name=Tom	 Greeting via query param
GET	/greet/{name}	 Greeting via path param
GET	/info/{id}?details=...	Combined path + query parameters
POST	/user	         Accepts JSON body (name, age)
```
### 📚 Book API Endpoints (JSON)
Method	Endpoint	Description
```
GET	/api/books	    List all books
GET	/api/books/{id}	    Get specific book
POST	/api/books	    Add a new book
PUT	/api/books/{id}	    Update a book
DELETE	/api/books/{id}	    Delete a book
```
### 🌐 Thymeleaf HTML Pages
```
/	      Home page
/login	      Login form
/books	      View all books
/books/paged  Paginated book list
/admin	      Admin dashboard
```
### 🧪 Async Task
Method	Endpoint	Description
```
GET	/async/send-email?to=email@x.com	  Send async test email
```
### 🛠 Admin Logs
Method	Endpoint	Description
```
GET	/admin/logs/test	          Trigger INFO/WARN/ERROR logs
GET	/admin/logs/tail?lines=50	  Show last N lines from log file
GET	/admin/logs/download              Download full log file
```
Log file path: logs/spring.log

### ✅ Requirements
Java 17+

Maven 3.8+

MySQL 8

Docker (optional)

### 🧪 Running Tests
```
mvn test
```
### 📚 Author
This project is developed as part of learning from Mastering Spring Boot 3.
Feel free to contribute or modify as needed!
