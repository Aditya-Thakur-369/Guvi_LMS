package records;

import java.util.Date;

public class IssueRecord {
    private String recordId;
    private String bookId;
    private String studentId;
    private Date issueDate;
    private Date dueDate;
    private Date returnDate;
    private double fineAmount;
    private String status; // ISSUED, RETURNED, OVERDUE

    public IssueRecord(String recordId, String bookId, String studentId) {
        this.recordId = recordId;
        this.bookId = bookId;
        this.studentId = studentId;
        this.issueDate = new Date();
        this.dueDate = new Date(System.currentTimeMillis() + 14L * 24 * 60 * 60 * 1000); // 14 days
        this.status = "ISSUED";
        this.fineAmount = 0.0;
    }

    // Constructor for loading from file
    public IssueRecord(String recordId, String bookId, String studentId, 
                      Date issueDate, Date dueDate, Date returnDate, 
                      double fineAmount, String status) {
        this.recordId = recordId;
        this.bookId = bookId;
        this.studentId = studentId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.fineAmount = fineAmount;
        this.status = status;
    }

    public void returnBook() {
        this.returnDate = new Date();
        this.status = "RETURNED";
        if (returnDate.after(dueDate)) {
            long diffDays = (returnDate.getTime() - dueDate.getTime()) / (24 * 60 * 60 * 1000);
            this.fineAmount = diffDays * 1.0; // $1 per day
        }
    }

    // Getters
    public String getRecordId() { return recordId; }
    public String getBookId() { return bookId; }
    public String getStudentId() { return studentId; }
    public Date getIssueDate() { return issueDate; }
    public Date getDueDate() { return dueDate; }
    public Date getReturnDate() { return returnDate; }
    public double getFineAmount() { return fineAmount; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return String.join(",",
            recordId,
            bookId,
            studentId,
            issueDate.toString(),
            dueDate.toString(),
            returnDate != null ? returnDate.toString() : "null",
            String.valueOf(fineAmount),
            status
        );
    }
}