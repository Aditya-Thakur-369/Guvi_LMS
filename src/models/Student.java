package models;

import java.util.*;

public class Student extends User {
    private List<String> issuedBooks;
    private double fine;
    private String course;
    private String semester;
    private String contactNumber;
    private String email;
    private Date membershipValidTill;

    public Student(String id, String name, String password, String course, 
                  String semester, String contactNumber, String email) {
        super(id, name, password, "STUDENT");
        this.issuedBooks = new ArrayList<>();
        this.fine = 0.0;
        this.course = course;
        this.semester = semester;
        this.contactNumber = contactNumber;
        this.email = email;
        this.membershipValidTill = new Date(System.currentTimeMillis() + 365L * 24 * 60 * 60 * 1000);
    }

    // Getters
    public List<String> getIssuedBooks() { return issuedBooks; }
    public double getFine() { return fine; }
    public String getCourse() { return course; }
    public String getSemester() { return semester; }
    public String getContactNumber() { return contactNumber; }
    public String getEmail() { return email; }
    public Date getMembershipValidTill() { return membershipValidTill; }

    // Setters
    public void setFine(double fine) { this.fine = fine; }
    public void setCourse(String course) { this.course = course; }
    public void setSemester(String semester) { this.semester = semester; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public void setEmail(String email) { this.email = email; }
    
    public void addIssuedBook(String bookId) {
        issuedBooks.add(bookId);
    }

    public void removeIssuedBook(String bookId) {
        issuedBooks.remove(bookId);
    }

    @Override
    public String toString() {
        return String.join(",", 
            super.toString(),
            course,
            semester,
            contactNumber,
            email,
            membershipValidTill.toString(),
            String.valueOf(fine),
            String.join(";", issuedBooks)
        );
    }
}