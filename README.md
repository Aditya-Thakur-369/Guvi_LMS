# 📚 Library Management System (LMS)

[![Java](https://img.shields.io/badge/Java-17-red)](https://www.oracle.com/java/)
[![JavaFX](https://img.shields.io/badge/JavaFX-17-blue)](https://openjfx.io/)

A robust command-line based Library Management System with optional JavaFX UI capabilities, designed to streamline library operations and enhance user experience.

> [!IMPORTANT]
> 🚧 **UNDER DEVELOPMENT** 🚧
>
> This project is currently in active development and is not ready for production use.
> Please note:
> - Features are still being implemented and tested
> - Documentation is being updated regularly
> - Core functionality is being prioritized
>
> Feel free to star the repository to follow the development progress!

## 🌟 Key Features

### Authentication & Security 🔐
- Simple authentication system for staff and students
- Role-based access control
- Password encryption
- Basic session management

### Book Management 📖
- Comprehensive book catalog management
- ISBN-based book tracking
- Book availability status
- Basic search capabilities
- Book categorization

### User Management 👥
- Student profile management
- Staff profile management
- Basic permissions system
- User activity logging

### Borrowing System 🔄
- Book borrowing and returns
- Due date tracking
- Basic overdue notifications
- Fine calculation system
- Borrowing history

### Reporting System 📊
- Basic borrowing statistics
- User activity logs
- Book utilization reports
- Fine collection summary
- Simple data exports

## 🛠️ Technical Architecture

### Core Technology Stack
- **Language**: Java 17
- **UI Options**: 
  - Command Line Interface (CLI)
  - JavaFX 17 (Optional GUI)
- **Data Storage**: File-based system
- **Build System**: Basic Java compilation

### Optional UI Technology
- **Framework**: JavaFX 17
- **Styling**: CSS3
- **Layout**: FXML
- **Components**: Basic JavaFX Controls

## 🚀 Getting Started

### Prerequisites
```bash
- Java Development Kit (JDK) 17 or higher
- JavaFX SDK 17 (if using GUI)
- IDE (IntelliJ IDEA/Eclipse) with JavaFX support
```

### Installation Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/library-management-system.git
   cd library-management-system
   ```

2. **Compile the project**
   ```bash
   javac -d build src/com/librarymanagement/*.java
   ```

3. **Run the application**
   ```bash
   java -cp build com.librarymanagement.Main
   ```

## 🔧 Project Structure

```plaintext
library-management-system/
├── src/
│   ├── com/
│   │   ├── librarymanagement/
│   │   │   ├── Main.java              # Main class to start the application
│   │   │   ├── model/                 # Classes for data models
│   │   │   ├── service/               # Classes for core logic
│   │   │   ├── dao/                   # Data access objects
│   │   │   └── util/                  # Utility classes
├── resources/
│   ├── data/                          # Data storage files
│   │   ├── books.txt
│   │   ├── students.txt
│   │   └── staff.txt
├── .gitignore
├── README.md
├── build/                             # Compiled class files
```

## 💻 Development

### Code Style
- Google Java Style Guide
- Clean Code principles
- Proper documentation
- Consistent formatting

### Command Line Interface
The system supports the following commands:
```plaintext
help                    - Show available commands
login                   - Login as student or staff
add-book               - Add a new book
search-book            - Search for books
borrow-book            - Borrow a book
return-book            - Return a book
list-books             - List all books
exit                   - Exit the system
```

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 👥 Authors

- **Your Name** - *Initial work* - [YourGitHub](https://github.com/Aditya-Thakur-369)

## 🙏 Acknowledgments

- JavaFX Documentation
- Java File I/O Documentation
- Stack Overflow Community

## 📧 Contact

Your Name - adityachauhan0369@gmail.com

Project Link: [https://github.com/Aditya-Thakur-0369/library-management-system](https://github.com/Aditya-Thakur-0369/Guvi_LMS)

---
⭐️ Star this repo if you find it helpful!
