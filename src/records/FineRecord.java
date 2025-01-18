package records;

import java.util.Date;

public class FineRecord {
    private String recordId;
    private String studentId;
    private double amount;
    private Date paymentDate;
    private String paymentMethod;
    private String issueRecordId;

    public FineRecord(String recordId, String studentId, double amount, 
                     String paymentMethod, String issueRecordId) {
        this.recordId = recordId;
        this.studentId = studentId;
        this.amount = amount;
        this.paymentDate = new Date();
        this.paymentMethod = paymentMethod;
        this.issueRecordId = issueRecordId;
    }

    // Constructor for loading from file
    public FineRecord(String recordId, String studentId, double amount, 
                     Date paymentDate, String paymentMethod, String issueRecordId) {
        this.recordId = recordId;
        this.studentId = studentId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.issueRecordId = issueRecordId;
    }

    // Getters
    public String getRecordId() { return recordId; }
    public String getStudentId() { return studentId; }
    public double getAmount() { return amount; }
    public Date getPaymentDate() { return paymentDate; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getIssueRecordId() { return issueRecordId; }

    @Override
    public String toString() {
        return String.join(",",
            recordId,
            studentId,
            String.valueOf(amount),
            paymentDate.toString(),
            paymentMethod,
            issueRecordId
        );
    }
}