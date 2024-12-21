# 📚 Library Management System (LMS)

[![Java](https://img.shields.io/badge/Java-17-red)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-green)](https://spring.io/projects/spring-boot)
[![JavaFX](https://img.shields.io/badge/JavaFX-17-blue)](https://openjfx.io/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-orange)](https://www.mysql.com/)

A robust, feature-rich Library Management System built with modern technologies to streamline library operations and enhance user experience.

## 🌟 Key Features

### Authentication & Security 🔐
- Dual authentication system for staff and students
- Role-based access control (RBAC)
- JWT-based secure authentication
- Password encryption using BCrypt
- Session management

### Book Management 📖
- Comprehensive book catalog management
- ISBN-based book tracking
- Multiple copies management
- Book availability status
- Advanced search and filtering capabilities
- Book categorization and location tracking

### User Management 👥
- Student profile management
- Staff profile management
- Role-based permissions
- Account status monitoring
- User activity tracking

### Borrowing System 🔄
- Real-time book borrowing and returns
- Due date tracking
- Automated overdue notifications
- Fine calculation system
- Borrowing history maintenance
- Multiple books borrowing support

### Attendance System ✅
- Student attendance tracking
- Check-in/check-out system
- Attendance reports generation
- Time-stamped entries
- Daily, weekly, and monthly attendance views

### Reporting System 📊
- Comprehensive borrowing analytics
- User activity reports
- Book utilization statistics
- Fine collection reports
- Attendance summary reports
- Custom report generation

## 🛠️ Technical Architecture

### Backend Technology Stack
- **Framework**: Spring Boot 3.2.0
- **Language**: Java 17
- **Database**: MySQL 8.0
- **ORM**: Hibernate/JPA
- **Security**: Spring Security
- **API Documentation**: Swagger/OpenAPI
- **Testing**: JUnit 5, Mockito

### Frontend Technology Stack
- **UI Framework**: JavaFX 17
- **Styling**: CSS3
- **Layout**: FXML
- **Components**: Custom JavaFX Controls
- **Charts**: JavaFX Charts

### Database Schema
- Normalized database design
- Referential integrity
- Indexed queries
- Optimized for performance

## 🚀 Getting Started

### Prerequisites
```bash
- Java Development Kit (JDK) 17 or higher
- Maven 3.8+
- MySQL 8.0
- IDE (IntelliJ IDEA/Eclipse) with JavaFX support
```

### Installation Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/library-management-system.git
   cd library-management-system
   ```

2. **Configure MySQL**
   ```bash
   # Create database
   mysql -u root -p
   CREATE DATABASE library_management;
   ```

3. **Update application.properties**
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/library_management
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

4. **Build the project**
   ```bash
   mvn clean install
   ```

5. **Run the application**
   ```bash
   java -jar target/library-management-system-1.0.0.jar
   ```

## 🔧 Project Structure

```plaintext
library-management-system/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/library/
│   │   │       ├── config/
│   │   │       ├── controller/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       ├── service/
│   │   │       └── ui/
│   │   └── resources/
│   └── test/
└── pom.xml
```

## 📋 API Endpoints

### Book Management
```plaintext
GET    /api/books          - Get all books
POST   /api/books          - Add a new book
PUT    /api/books/{id}     - Update a book
DELETE /api/books/{id}     - Delete a book
GET    /api/books/search   - Search books
```

### User Management
```plaintext
GET    /api/users          - Get all users
POST   /api/users          - Add a new user
PUT    /api/users/{id}     - Update a user
DELETE /api/users/{id}     - Delete a user
```

### Borrowing
```plaintext
POST   /api/borrow         - Borrow a book
PUT    /api/borrow/return  - Return a book
GET    /api/borrow/user    - Get user's borrowed books
```

## 💻 Development

### Code Style
- Google Java Style Guide
- Clean Code principles
- SOLID design principles
- Proper documentation

### Testing
```bash
# Run unit tests
mvn test

# Run integration tests
mvn verify

# Generate coverage report
mvn jacoco:report
```

## 🔄 CI/CD Pipeline

- GitHub Actions workflow
- Automated testing
- Code quality checks
- Build verification
- Docker image creation

## 📦 Dependencies

```xml
<dependencies>
    <!-- Spring Boot -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    
    <!-- JavaFX -->
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
    </dependency>
    
    <!-- Database -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
</dependencies>
```

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👥 Authors

- **Your Name** - *Initial work* - [YourGitHub](https://github.com/yourusername)

## 🙏 Acknowledgments

- Spring Boot Documentation
- JavaFX Documentation
- MySQL Documentation
- Stack Overflow Community

## 📧 Contact

Your Name - your.email@example.com

Project Link: [https://github.com/yourusername/library-management-system](https://github.com/yourusername/library-management-system)

---
⭐️ Star this repo if you find it helpful!
