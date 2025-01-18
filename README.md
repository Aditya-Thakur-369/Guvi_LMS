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
- Enhanced authentication system for staff and students
- Role-based access control with session management
- Password hashing and encryption
- Secure user validation

### Book Management 📖
- Comprehensive book catalog management
- Track book quantity and availability
- ISBN and price tracking
- Book location management (shelf/row)
- Category-based organization
- Advanced search capabilities

### User Management 👥
- Student profile management with course and semester tracking
- Staff profile management
- Membership validity tracking
- Contact information management
- Detailed user activity logging

### Borrowing System 🔄
- Book borrowing and returns
- Due date tracking and reminders
- Enhanced overdue notifications
- Flexible fine calculation system
- Multiple payment methods
- Detailed borrowing history

### Reporting System 📊
- Advanced borrowing statistics
- Fine collection reports
- Overdue book tracking
- Book utilization reports
- Inventory management
- Data export capabilities

## 🛠️ Technical Architecture

### Core Technology Stack
- **Language**: Java 17
- **UI Options**: 
  - Command Line Interface (CLI)
  - JavaFX 17 (Optional GUI)
- **Data Storage**: Enhanced file-based system with backups
- **Build System**: Basic Java compilation

### Optional UI Technology
- **Framework**: JavaFX 17
- **Styling**: CSS3
- **Layout**: FXML
- **Components**: Advanced JavaFX Controls

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
library_management_system/
├── src/
│   ├── models/
│   │   ├── Book.java
│   │   ├── User.java
│   │   ├── Student.java
│   │   └── Admin.java
│   ├── records/
│   │   ├── IssueRecord.java
│   │   └── FineRecord.java
│   ├── auth/
│   │   └── AuthenticationManager.java
│   ├── storage/
│   │   └── FileHandler.java
│   ├── LibrarySystem.java
│   └── Main.java
└── library_data/
    ├── books.txt        # Format: B001,Java Programming,John Doe,Programming,5,978-0-123456-78-9,29.99,A1-R2,true,null
    ├── users.txt        # Format: S001,Jane Smith,hashedPassword,STUDENT,Computer Science,3rd,1234567890,jane@email.com,2024-12-31,0.0,B001;B002
    ├── issue_records.txt # Format: IR001,B001,S001,2024-01-18 14:30:00,2024-02-01 14:30:00,null,0.0,ISSUED
    └── fine_records.txt # Format: FR001,S001,5.00,2024-01-18 14:30:00,CASH,IR001                      
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
search-book            - Search for books by various criteria
borrow-book            - Borrow a book
return-book            - Return a book
list-books             - List all books
manage-profile         - Update user profile
view-fines             - Check and pay fines
exit                   - Exit the system
```

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 👥 Authors

- **Jyotiraditya Chauhan** - [Aditya-Thakur-369](https://github.com/Aditya-Thakur-369)

## 🙏 Acknowledgments

- JavaFX Documentation
- Java File I/O Documentation
- Stack Overflow Community

## 📧 Contact

Name - Jyotiraditya Chauhan
Email - adityachauhan0369@gmail.com
Project Link: [https://github.com/Aditya-Thakur-0369/Guvi_LMS](https://github.com/Aditya-Thakur-0369/Guvi_LMS)

> [!NOTE]
> While this system is primarily Java-based, future versions may include web-based features using Next.js and React for enhanced user experience.

---
⭐️ Star this repo if you find it helpful!
