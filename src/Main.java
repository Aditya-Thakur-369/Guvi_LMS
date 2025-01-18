import java.util.*;
import java.text.SimpleDateFormat;
import models.*;
import records.*;

public class Main {
    private static LibrarySystem library;
    private static Scanner scanner;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) {
        library = new LibrarySystem();
        scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("\n=== Library Management System ===");
                System.out.println("1. Student Portal");
                System.out.println("2. Admin Portal");
                System.out.println("3. Exit");
                System.out.print("Choose user type: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        studentPortal();
                        break;
                    case 2:
                        adminPortal();
                        break;
                    case 3:
                        System.out.println("Saving all data...");
                        library.saveAllData();
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
            scanner.close();
        }
    }

    private static void studentPortal() {
        while (true) {
            try {
                System.out.println("\n=== Student Portal ===");
                System.out.println("1. Login");
                System.out.println("2. Request Account");
                System.out.println("3. Back to Main Menu");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        studentLogin();
                        break;
                    case 2:
                        requestStudentAccount();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    private static void adminPortal() {
        while (true) {
            try {
                System.out.println("\n=== Admin Portal ===");
                System.out.println("\nMaster Admin Password: admin123\n");
                System.out.println("1. Login");
                System.out.println("2. Register New Admin");
                System.out.println("3. Back to Main Menu");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        adminLogin();
                        break;
                    case 2:
                        registerAdmin();
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }


    private static void studentLogin() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (library.login(id, password)) {
            if (id.startsWith("S")) {
                studentMenu();
            } else {
                System.out.println("Invalid student credentials!");
            }
        } else {
            System.out.println("Invalid credentials!");
        }
    }

    private static void adminLogin() {
        System.out.print("Enter Admin ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (library.login(id, password)) {
            if (id.startsWith("A")) {
                adminMenu();
            } else {
                
                System.out.println("Invalid admin credentials!");
            }
        } else {
          
            System.out.println("Invalid credentials!");
        }
    }

    private static void requestStudentAccount() {
        try {
            System.out.println("\n=== Request Student Account ===");
            System.out.print("Enter Full Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Course: ");
            String course = scanner.nextLine();
            System.out.print("Enter Semester: ");
            String semester = scanner.nextLine();
            System.out.print("Enter Contact Number: ");
            String contact = scanner.nextLine();
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();

            String requestId = library.createStudentRequest(name, course, semester, contact, email);
            System.out.println("\nAccount request submitted successfully!");
            System.out.println("Your Request ID is: " + requestId);
            System.out.println("Please contact the admin with this Request ID to get your credentials.");

        } catch (Exception e) {
            System.out.println("Error submitting request: " + e.getMessage());
        }
    }

    private static void registerAdmin() {
        try {
            System.out.println("\n=== Register New Admin ===");
            System.out.print("Enter Master Admin Password: ");
            String masterPassword = scanner.nextLine();

            if (!library.verifyMasterPassword(masterPassword)) {
                System.out.println("Invalid master password!");
                return;
            }

            System.out.print("Enter Admin Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            String adminId = library.registerAdmin(name, password);
            System.out.println("\nAdmin registered successfully!");
            System.out.println("Your Admin ID is: " + adminId);
            System.out.println("Please use this ID and your password to login.");

        } catch (Exception e) {
            System.out.println("Error registering admin: " + e.getMessage());
        }
    }


    private static void adminMenu() {
        while (true) {
            try {
                System.out.println("\n=== Admin Menu ===");
                System.out.println("1. Book Management");
                System.out.println("2. Student Management");
                System.out.println("3. Student Account Requests");
                System.out.println("4. Reports");
                System.out.println("5. Logout");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        bookManagementMenu();
                        break;
                    case 2:
                        studentManagementMenu();
                        break;
                    case 3:
                        handleStudentRequests();
                        break;
                    case 4:
                        reportsMenu();
                        break;
                    case 5:
                        library.logout();
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    private static void handleStudentRequests() {
        while (true) {
            try {
                System.out.println("\n=== Student Account Requests ===");
                List<StudentRequest> requests = library.getPendingStudentRequests();
                
                if (requests.isEmpty()) {
                    System.out.println("No pending requests.");
                    return;
                }

                System.out.println("\nPending Requests:");
                System.out.printf("%-10s %-20s %-15s %-15s %-15s %-25s%n",
                    "Request ID", "Name", "Course", "Semester", "Contact", "Email");
                System.out.println("-".repeat(100));

                for (StudentRequest req : requests) {
                    System.out.printf("%-10s %-20s %-15s %-15s %-15s %-25s%n",
                        req.getRequestId(),
                        truncateString(req.getName(), 20),
                        truncateString(req.getCourse(), 15),
                        req.getSemester(),
                        req.getContact(),
                        truncateString(req.getEmail(), 25)
                    );
                }

                System.out.print("\nEnter Request ID to process (or 0 to go back): ");
                String requestId = scanner.nextLine();

                if (requestId.equals("0")) {
                    return;
                }

                System.out.print("Approve request? (Y/N): ");
                String approve = scanner.nextLine();

                if (approve.equalsIgnoreCase("Y")) {
                    StudentCredentials creds = library.approveStudentRequest(requestId);
                    System.out.println("\nStudent account created successfully!");
                    System.out.println("Student ID: " + creds.getId());
                    System.out.println("Password: " + creds.getPassword());
                    System.out.println("\nPlease provide these credentials to the student.");
                } else {
                    library.rejectStudentRequest(requestId);
                    System.out.println("Request rejected.");
                }

            } catch (Exception e) {
                System.out.println("Error processing request: " + e.getMessage());
            }
        }
    }

    private static void bookManagementMenu() {
        while (true) {
            System.out.println("\n=== Book Management ===");
            System.out.println("1. Add New Book");
            System.out.println("2. Update Book");
            System.out.println("3. Remove Book");
            System.out.println("4. Search Books");
            System.out.println("5. View All Books");
            System.out.println("6. Back to Main Menu");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addNewBook();
                        break;
                    case 2:
                        updateBook();
                        break;
                    case 3:
                        removeBook();
                        break;
                    case 4:
                        searchBooks();
                        break;
                    case 5:
                        viewAllBooks();
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    private static void studentManagementMenu() {
        while (true) {
            System.out.println("\n=== Student Management ===");
            System.out.println("1. Add New Student");
            System.out.println("2. Update Student");
            System.out.println("3. View Student Details");
            System.out.println("4. Back to Main Menu");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addNewStudent();
                        break;
                    case 2:
                        updateStudent();
                        break;
                    case 3:
                        viewStudentDetails();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    private static void studentMenu() {
        while (true) {
            System.out.println("\n=== Student Menu ===");
            System.out.println("1. Search Books");
            System.out.println("2. View Issued Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Pay Fine");
            System.out.println("6. View Profile");
            System.out.println("7. Logout");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        searchBooks();
                        break;
                    case 2:
                        viewIssuedBooks();
                        break;
                    case 3:
                        issueBook();
                        break;
                    case 4:
                        returnBook();
                        break;
                    case 5:
                        payFine();
                        break;
                    case 6:
                        viewProfile();
                        break;
                    case 7:
                        library.logout();
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    private static void reportsMenu() {
        while (true) {
            System.out.println("\n=== Reports Menu ===");
            System.out.println("1. View All Books");
            System.out.println("2. View Overdue Books");
            System.out.println("3. View Fine Collection Report");
            System.out.println("4. Back to Main Menu");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        viewAllBooks();
                        break;
                    case 2:
                        viewOverdueBooks();
                        break;
                    case 3:
                        viewFineCollectionReport();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    // Helper methods for menu actions
    private static void addNewBook() {
        try {
            System.out.println("\n=== Add New Book ===");
            System.out.print("Enter Title: ");
            String title = scanner.nextLine();
            System.out.print("Enter Author: ");
            String author = scanner.nextLine();
            System.out.print("Enter Category: ");
            String category = scanner.nextLine();
            System.out.print("Enter Quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter ISBN: ");
            String isbn = scanner.nextLine();
            System.out.print("Enter Price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Enter Location (Shelf-Row): ");
            String location = scanner.nextLine();

            library.addBook(title, author, category, quantity, isbn, price, location);
            System.out.println("Book added successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter correct data types.");
            scanner.nextLine();
        }
    }

    private static void updateBook() {
        try {
            System.out.println("\n=== Update Book ===");
            System.out.print("Enter Book ID to update: ");
            String bookId = scanner.nextLine();
            
            // Add your book update logic here
            System.out.println("Book updated successfully!");
        } catch (Exception e) {
            System.out.println("Error updating book: " + e.getMessage());
        }
    }

    private static void removeBook() {
        try {
            System.out.println("\n=== Remove Book ===");
            System.out.print("Enter Book ID to remove: ");
            String bookId = scanner.nextLine();
            
            // Add your book removal logic here
            System.out.println("Book removed successfully!");
        } catch (Exception e) {
            System.out.println("Error removing book: " + e.getMessage());
        }
    }

    private static void addNewStudent() {
        try {
            System.out.println("\n=== Add New Student ===");
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();
            System.out.print("Enter Course: ");
            String course = scanner.nextLine();
            System.out.print("Enter Semester: ");
            String semester = scanner.nextLine();
            System.out.print("Enter Contact Number: ");
            String contact = scanner.nextLine();
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();

            library.addStudent(name, password, course, semester, contact, email);
            System.out.println("Student added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    private static void updateStudent() {
        try {
            System.out.println("\n=== Update Student ===");
            System.out.print("Enter Student ID to update: ");
            String studentId = scanner.nextLine();
            
            // Add your student update logic here
            System.out.println("Student updated successfully!");
        } catch (Exception e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }

    private static void viewStudentDetails() {
        try {
            System.out.println("\n=== View Student Details ===");
            System.out.print("Enter Student ID: ");
            String studentId = scanner.nextLine();
            
            // Add your student details view logic here
        } catch (Exception e) {
            System.out.println("Error viewing student details: " + e.getMessage());
        }
    }

    private static void searchBooks() {
        try {
            System.out.println("\n=== Search Books ===");
            System.out.println("1. Search by Title");
            System.out.println("2. Search by Author");
            System.out.println("3. Search by Category");
            System.out.println("4. Search by ISBN");
            System.out.print("Choose search type: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter search term: ");
            String searchTerm = scanner.nextLine();

            List<Book> results = library.searchBooks(choice, searchTerm);
            displayBooks(results);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
        }
    }

    private static void viewAllBooks() {
        try {
            List<Book> allBooks = library.getAvailableBooks();
            displayBooks(allBooks);
        } catch (Exception e) {
            System.out.println("Error viewing books: " + e.getMessage());
        }
    }

    private static void viewIssuedBooks() {
        try {
            List<Book> issuedBooks = library.getIssuedBooks();
            displayBooks(issuedBooks);
        } catch (Exception e) {
            System.out.println("Error viewing issued books: " + e.getMessage());
        }
    }

    private static void issueBook() {
        try {
            System.out.println("\n=== Issue Book ===");
            System.out.print("Enter Book ID to issue: ");
            String bookId = scanner.nextLine();
            
            if (library.issueBook(bookId)) {
                System.out.println("Book issued successfully!");
            } else {
                System.out.println("Failed to issue book. Book may not be available.");
            }
        } catch (Exception e) {
            System.out.println("Error issuing book: " + e.getMessage());
        }
    }

    private static void returnBook() {
        try {
            System.out.println("\n=== Return Book ===");
            System.out.print("Enter Book ID to return: ");
            String bookId = scanner.nextLine();
            
            if (library.returnBook(bookId)) {
                System.out.println("Book returned successfully!");
            } else {
                System.out.println("Failed to return book. Please check the book ID.");
            }
        } catch (Exception e) {
            System.out.println("Error returning book: " + e.getMessage());
        }
    }

    private static void payFine() {
        try {
            System.out.println("\n=== Pay Fine ===");
            System.out.print("Enter amount to pay: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Enter payment method (CASH/CARD): ");
            String paymentMethod = scanner.nextLine();
            
            library.payFine(amount, paymentMethod);
            System.out.println("Fine paid successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid amount.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Error paying fine: " + e.getMessage());
        }
    }

    private static void viewProfile() {
        try {
            System.out.println("\n=== Student Profile ===");
            // Add your profile view logic here
        } catch (Exception e) {
            System.out.println("Error viewing profile: " + e.getMessage());
        }
    }

    private static void viewOverdueBooks() {
        try {
            List<IssueRecord> overdueBooks = library.getOverdueBooks();
            if (overdueBooks.isEmpty()) {
                System.out.println("No overdue books found.");
                return;
            }
            
            System.out.println("\nOverdue Books:");
            System.out.printf("%-10s %-10s %-10s %-20s %-20s%n",
                "Record ID", "Book ID", "Student ID", "Due Date", "Fine Amount");
            System.out.println("-".repeat(80));
            
            for (IssueRecord record : overdueBooks) {
                System.out.printf("%-10s %-10s %-10s %-20s $%-19.2f%n",
                    record.getRecordId(),
                    record.getBookId(),
                    record.getStudentId(),
                    dateFormat.format(record.getDueDate()),
                    record.getFineAmount()
                );
            }
        } catch (Exception e) {
            System.out.println("Error viewing overdue books: " + e.getMessage());
        }
    }

    private static void viewFineCollectionReport() {
        try {
            System.out.println("\n=== Fine Collection Report ===");
            // Add your fine collection report logic here
        } catch (Exception e) {
            System.out.println("Error viewing fine collection report: " + e.getMessage());
        }
    }

    private static void displayBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books found!");
            return;
        }

        System.out.println("\nBooks Found:");
        System.out.printf("%-10s %-30s %-20s %-15s %-10s %-15s %-10s %-10s%n",
            "ID", "Title", "Author", "Category", "Quantity", "ISBN", "Price", "Location");
        System.out.println("-".repeat(120));

        for (Book book : books) {
            System.out.printf("%-10s %-30s %-20s %-15s %-10d %-15s $%-9.2f %-10s%n",
                book.getId(),
                truncateString(book.getTitle(), 30),
                truncateString(book.getAuthor(), 20),
                truncateString(book.getCategory(), 15),
                book.getQuantity(),
                book.getIsbn(),
                book.getPrice(),
                book.getLocation()
            );
        }
    }

    private static String truncateString(String str, int length) {
        if (str == null) return "";
        if (str.length() <= length) return str;
        return str.substring(0, length - 3) + "...";
    }
}