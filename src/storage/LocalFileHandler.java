package storage;

import records.*;
import java.io.*;
import models.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
 
public class LocalFileHandler {
    private static final String DATA_DIR = "library_data/";
    private static final String BOOKS_FILE = DATA_DIR + "books.txt";
    private static final String USERS_FILE = DATA_DIR + "users.txt";
    private static final String ISSUE_RECORDS_FILE = DATA_DIR + "issue_records.txt";
    private static final String FINE_RECORDS_FILE = DATA_DIR + "fine_records.txt";

private static final String STUDENT_REQUESTS_FILE = DATA_DIR + "student_requests.txt";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

    static {
        new File(DATA_DIR).mkdirs();
    }

    public static void saveBooks(List<Book> books) {
        saveToFile(BOOKS_FILE, books);
    }

    public static void saveUsers(List<User> users) {
        saveToFile(USERS_FILE, users);
    }

    public static void saveIssueRecords(List<IssueRecord> records) {
        saveToFile(ISSUE_RECORDS_FILE, records);
    }

    public static void saveFineRecords(List<FineRecord> records) {
        saveToFile(FINE_RECORDS_FILE, records);
    }

    private static void saveToFile(String filename, List<?> items) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Object item : items) {
                writer.println(item.toString());
            }
        } catch (IOException e) {
            System.err.println("Error saving to " + filename + ": " + e.getMessage());
        }
    }

    public static List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Book book = new Book(
                    parts[0], // id
                    parts[1], // title
                    parts[2], // author
                    parts[3], // category
                    Integer.parseInt(parts[4]), // quantity
                    parts[5], // isbn
                    Double.parseDouble(parts[6]), // price
                    parts[7]  // location
                );
                book.setAvailable(Boolean.parseBoolean(parts[8]));
                book.setIssuedTo(parts[9].equals("null") ? null : parts[9]);
                books.add(book);
            }
        } catch (IOException e) {
            System.err.println("Error loading books: " + e.getMessage());
        }
        return books;
    }

    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[3].equals("ADMIN")) {
                    users.add(new Admin(parts[0], parts[1], parts[2]));
                } else {
                    Student student = new Student(
                        parts[0], // id
                        parts[1], // name
                        parts[2], // password
                        parts[4], // course
                        parts[5], // semester
                        parts[6], // contactNumber
                        parts[7]  // email
                    );
                    // Parse membership date
                    try {
                        Date membershipDate = DATE_FORMAT.parse(parts[8]);
                        // Handle membership date if needed
                    } catch (ParseException e) {
                        System.err.println("Error parsing date: " + e.getMessage());
                    }
                    student.setFine(Double.parseDouble(parts[9]));
                    if (parts.length > 10 && !parts[10].isEmpty()) {
                        Arrays.stream(parts[10].split(";"))
                              .forEach(student::addIssuedBook);
                    }
                    users.add(student);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading users: " + e.getMessage());
        }
        return users;
    }

    public static List<IssueRecord> loadIssueRecords() {
        List<IssueRecord> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ISSUE_RECORDS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                try {
                    IssueRecord record = new IssueRecord(
                        parts[0], // recordId
                        parts[1], // bookId
                        parts[2], // studentId
                        DATE_FORMAT.parse(parts[3]), // issueDate
                        DATE_FORMAT.parse(parts[4]), // dueDate
                        parts[5].equals("null") ? null : DATE_FORMAT.parse(parts[5]), // returnDate
                        Double.parseDouble(parts[6]), // fineAmount
                        parts[7]  // status
                    );
                    records.add(record);
                } catch (ParseException e) {
                    System.err.println("Error parsing date: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading issue records: " + e.getMessage());
        }
        return records;
    }

    public static List<FineRecord> loadFineRecords() {
        List<FineRecord> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FINE_RECORDS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                try {
                    FineRecord record = new FineRecord(
                        parts[0], // recordId
                        parts[1], // studentId
                        Double.parseDouble(parts[2]), // amount
                        DATE_FORMAT.parse(parts[3]), // paymentDate
                        parts[4], // paymentMethod
                        parts[5]  // issueRecordId
                    );
                    records.add(record);
                } catch (ParseException e) {
                    System.err.println("Error parsing date: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading fine records: " + e.getMessage());
        }
        return records;
    }

    public static User findUser(String id) {
        return loadUsers().stream()
                         .filter(user -> user.getId().equals(id))
                         .findFirst()
                         .orElse(null);
    }



public static void saveStudentRequests(List<StudentRequest> requests) {
    saveToFile(STUDENT_REQUESTS_FILE, requests);
}

public static List<StudentRequest> loadStudentRequests() {
    List<StudentRequest> requests = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(STUDENT_REQUESTS_FILE))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            StudentRequest request = new StudentRequest(
                parts[0], // requestId
                parts[1], // name
                parts[2], // course
                parts[3], // semester
                parts[4], // contact
                parts[5]  // email
            );
            request.setStatus(parts[6]); // status
            requests.add(request);
        }
    } catch (IOException e) {
        System.err.println("Error loading student requests: " + e.getMessage());
    }
    return requests;
}

}