
package models;

public class StudentRequest {
    private String requestId;
    private String name;
    private String course;
    private String semester;
    private String contact;
    private String email;
    private String status; // PENDING, APPROVED, REJECTED

    public StudentRequest(String requestId, String name, String course, 
                         String semester, String contact, String email) {
        this.requestId = requestId;
        this.name = name;
        this.course = course;
        this.semester = semester;
        this.contact = contact;
        this.email = email;
        this.status = "PENDING";
    }

    // Getters
    public String getRequestId() { return requestId; }
    public String getName() { return name; }
    public String getCourse() { return course; }
    public String getSemester() { return semester; }
    public String getContact() { return contact; }
    public String getEmail() { return email; }
    public String getStatus() { return status; }

    // Setters
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return String.join(",",
            requestId,
            name,
            course,
            semester,
            contact,
            email,
            status
        );
    }
}