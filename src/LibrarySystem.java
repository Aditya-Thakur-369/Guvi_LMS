import models.*;
import records.*;
import auth.AuthenticationManager;
import storage.LocalFileHandler;
import java.util.*;
import java.util.stream.Collectors;

public class LibrarySystem {
    private List<Book> books;
    private List<User> users;
    private List<IssueRecord> issueRecords;
    private List<FineRecord> fineRecords;
    private User currentUser;
    private AuthenticationManager authManager;


    private List<StudentRequest> studentRequests;
private static final String MASTER_ADMIN_PASSWORD = "admin123"; // In real system, this should be more secure



    public LibrarySystem() {
        this.books = LocalFileHandler.loadBooks();
        this.users = LocalFileHandler.loadUsers();
        this.issueRecords = LocalFileHandler.loadIssueRecords();
        this.fineRecords = LocalFileHandler.loadFineRecords();
        this.authManager = AuthenticationManager.getInstance();
        this.currentUser = null;
    }

    

    // Authentication methods
    public boolean login(String id, String password) {
        if (authManager.login(id, password)) {
            System.out.println("Authenticated as " + id);
            currentUser = authManager.getCurrentUser(id);
            return true;
        }
        return false;
    }

    public void logout() {
        if (currentUser != null) {
            authManager.logout(currentUser.getId());
            currentUser = null;
        }
    }

    // Book management methods
    public void addBook(String title, String author, String category, 
                       int quantity, String isbn, double price, String location) {
        if (isAdmin()) {
            String id = generateBookId();
            Book book = new Book(id, title, author, category, quantity, isbn, price, location);
            books.add(book);
            LocalFileHandler.saveBooks(books);
        }
    }

    public void updateBook(String bookId, String title, String author, String category, 
                          int quantity, String isbn, double price, String location) {
        if (isAdmin()) {
            books.stream()
                 .filter(b -> b.getId().equals(bookId))
                 .findFirst()
                 .ifPresent(book -> {
                     // Update book details
                     books.remove(book);
                     Book updatedBook = new Book(bookId, title, author, category, 
                                               quantity, isbn, price, location);
                     books.add(updatedBook);
                     LocalFileHandler.saveBooks(books);
                 });
        }
    }

    public void removeBook(String bookId) {
        if (isAdmin()) {
            books.removeIf(book -> book.getId().equals(bookId));
            LocalFileHandler.saveBooks(books);
        }
    }

    // Student management methods
    public void addStudent(String name, String password, String course, 
                         String semester, String contact, String email) {
        if (isAdmin()) {
            String id = generateStudentId();
            Student student = new Student(id, name, password, course, semester, contact, email);
            users.add(student);
            LocalFileHandler.saveUsers(users);
        }
    }

    public void updateStudent(String studentId, String course, String semester, 
                            String contact, String email) {
        users.stream()
             .filter(u -> u instanceof Student && u.getId().equals(studentId))
             .map(u -> (Student) u)
             .findFirst()
             .ifPresent(student -> {
                 student.setCourse(course);
                 student.setSemester(semester);
                 student.setContactNumber(contact);
                 student.setEmail(email);
                 LocalFileHandler.saveUsers(users);
             });
    }

    // Book issue methods
    public boolean issueBook(String bookId) {
        if (currentUser instanceof Student) {
            Student student = (Student) currentUser;
            Book book = findBook(bookId);
            
            if (book != null && book.isAvailable() && book.getQuantity() > 0) {
                String recordId = generateIssueRecordId();
                IssueRecord record = new IssueRecord(recordId, bookId, student.getId());
                
                book.setQuantity(book.getQuantity() - 1);
                if (book.getQuantity() == 0) {
                    book.setAvailable(false);
                }
                
                student.addIssuedBook(bookId);
                issueRecords.add(record);
                
                LocalFileHandler.saveBooks(books);
                LocalFileHandler.saveUsers(users);
                LocalFileHandler.saveIssueRecords(issueRecords);
                return true;
            }
        }
        return false;
    }

    public boolean returnBook(String bookId) {
        if (currentUser instanceof Student) {
            Student student = (Student) currentUser;
            Book book = findBook(bookId);
            
            if (book != null) {
                Optional<IssueRecord> record = issueRecords.stream()
                    .filter(r -> r.getBookId().equals(bookId) && 
                               r.getStudentId().equals(student.getId()) &&
                               r.getStatus().equals("ISSUED"))
                    .findFirst();
                
                if (record.isPresent()) {
                    IssueRecord issueRecord = record.get();
                    issueRecord.returnBook();
                    
                    book.setQuantity(book.getQuantity() + 1);
                    book.setAvailable(true);
                    student.removeIssuedBook(bookId);
                    
                    if (issueRecord.getFineAmount() > 0) {
                        student.setFine(student.getFine() + issueRecord.getFineAmount());
                    }
                    
                    LocalFileHandler.saveBooks(books);
                    LocalFileHandler.saveUsers(users);
                    LocalFileHandler.saveIssueRecords(issueRecords);
                    return true;
                }
            }
        }
        return false;
    }

    // Fine management methods
    public void payFine(double amount, String paymentMethod) {
        if (currentUser instanceof Student) {
            Student student = (Student) currentUser;
            if (student.getFine() >= amount) {
                String recordId = generateFineRecordId();
                FineRecord record = new FineRecord(recordId, student.getId(), 
                                                 amount, paymentMethod, null);
                student.setFine(student.getFine() - amount);
                fineRecords.add(record);
                
                LocalFileHandler.saveUsers(users);
                LocalFileHandler.saveFineRecords(fineRecords);
            }
        }
    }

    // Search methods
    public List<Book> searchBooks(int searchType, String searchTerm) {
        List<Book> results = new ArrayList<>();
        searchTerm = searchTerm.toLowerCase();

        for (Book book : books) {
            switch (searchType) {
                case 1: // Title
                    if (book.getTitle().toLowerCase().contains(searchTerm)) {
                        results.add(book);
                    }
                    break;
                case 2: // Author
                    if (book.getAuthor().toLowerCase().contains(searchTerm)) {
                        results.add(book);
                    }
                    break;
                case 3: // Category
                    if (book.getCategory().toLowerCase().contains(searchTerm)) {
                        results.add(book);
                    }
                    break;
                case 4: // ISBN
                    if (book.getIsbn().toLowerCase().contains(searchTerm)) {
                        results.add(book);
                    }
                    break;
            }
        }
        return results;
    }

    // Utility methods
    private String generateBookId() {
        return "B" + String.format("%03d", books.size() + 1);
    }

    private String generateStudentId() {
        return "S" + String.format("%03d", users.size() + 1);
    }

    private String generateIssueRecordId() {
        return "IR" + String.format("%03d", issueRecords.size() + 1);
    }

    private String generateFineRecordId() {
        return "FR" + String.format("%03d", fineRecords.size() + 1);
    }

    private Book findBook(String bookId) {
        return books.stream()
                   .filter(b -> b.getId().equals(bookId))
                   .findFirst()
                   .orElse(null);
    }

    private boolean isAdmin() {
        return currentUser != null && currentUser instanceof Admin;
    }

    public void saveAllData() {
        LocalFileHandler.saveBooks(books);
        LocalFileHandler.saveUsers(users);
        LocalFileHandler.saveIssueRecords(issueRecords);
        LocalFileHandler.saveFineRecords(fineRecords);
    }

    // Getter methods for reports
    public List<Book> getAvailableBooks() {
        return books.stream()
                   .filter(Book::isAvailable)
                   .toList();
    }

    public List<IssueRecord> getOverdueBooks() {
        Date now = new Date();
        return issueRecords.stream()
                          .filter(r -> r.getStatus().equals("ISSUED") && 
                                     now.after(r.getDueDate()))
                          .toList();
    }

    public List<Book> getIssuedBooks() {
        if (currentUser instanceof Student) {
            Student student = (Student) currentUser;
            return student.getIssuedBooks().stream()
                         .map(this::findBook)
                         .filter(Objects::nonNull)
                         .toList();
        }
        return new ArrayList<>();
    }



public String createStudentRequest(String name, String course, String semester, 
                                 String contact, String email) {
    String requestId = "REQ" + String.format("%03d", studentRequests.size() + 1);
    StudentRequest request = new StudentRequest(requestId, name, course, 
                                              semester, contact, email);
    studentRequests.add(request);
    saveStudentRequests();
    return requestId;
}

public List<StudentRequest> getPendingStudentRequests() {
    return studentRequests.stream()
                         .filter(req -> req.getStatus().equals("PENDING"))
                         .collect(Collectors.toList());
}

public StudentCredentials approveStudentRequest(String requestId) {
    StudentRequest request = findStudentRequest(requestId);
    if (request != null && request.getStatus().equals("PENDING")) {
        String studentId = generateStudentId();
        String password = generatePassword();
        
        Student student = new Student(studentId, request.getName(), password,
                                    request.getCourse(), request.getSemester(),
                                    request.getContact(), request.getEmail());
        
        users.add(student);
        request.setStatus("APPROVED");
        
        LocalFileHandler. saveUsers(users);
        saveStudentRequests();
        
        return new StudentCredentials(studentId, password);
    }
    return null;
}

public void rejectStudentRequest(String requestId) {
    StudentRequest request = findStudentRequest(requestId);
    if (request != null && request.getStatus().equals("PENDING")) {
        request.setStatus("REJECTED");
        saveStudentRequests();
    }
}

public boolean verifyMasterPassword(String password) {
    return MASTER_ADMIN_PASSWORD.equals(password);
}

public String registerAdmin(String name, String password) {
    String adminId = generateAdminId();
    Admin admin = new Admin(adminId, name, password);
    users.add(admin);
   LocalFileHandler.saveUsers(users);
    return adminId;
}

private String generatePassword() {
    // Generate a random 8-character password
    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    StringBuilder sb = new StringBuilder();
    Random random = new Random();
    for (int i = 0; i < 8; i++) {
        sb.append(chars.charAt(random.nextInt(chars.length())));
    }
    return sb.toString();
}

private String generateAdminId() {
    return "A" + String.format("%03d", users.stream()
                                           .filter(u -> u instanceof Admin)
                                           .count() + 1);
}

private StudentRequest findStudentRequest(String requestId) {
    return studentRequests.stream()
                         .filter(req -> req.getRequestId().equals(requestId))
                         .findFirst()
                         .orElse(null);
}

private void saveStudentRequests() {
    LocalFileHandler.saveStudentRequests(studentRequests);
}
    
}