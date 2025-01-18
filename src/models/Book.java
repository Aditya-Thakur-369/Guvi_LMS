package models;


public class Book {
    private String id;
    private String title;
    private String author;
    private String category;
    private int quantity;
    private String isbn;
    private double price;
    private String location;
    private boolean isAvailable;
    private String issuedTo;

    public Book(String id, String title, String author, String category, 
                int quantity, String isbn, double price, String location) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.quantity = quantity;
        this.isbn = isbn;
        this.price = price;
        this.location = location;
        this.isAvailable = true;
        this.issuedTo = null;
    }

    // Getters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public int getQuantity() { return quantity; }
    public String getIsbn() { return isbn; }
    public double getPrice() { return price; }
    public String getLocation() { return location; }
    public boolean isAvailable() { return isAvailable; }
    public String getIssuedTo() { return issuedTo; }

    // Setters
    public void setQuantity(int quantity) { 
        this.quantity = quantity;
        this.isAvailable = quantity > 0;
    }
    public void setAvailable(boolean available) { this.isAvailable = available; }
    public void setIssuedTo(String studentId) { this.issuedTo = studentId; }

    @Override
    public String toString() {
        return String.join(",", 
            id,
            title,
            author,
            category,
            String.valueOf(quantity),
            isbn,
            String.valueOf(price),
            location,
            String.valueOf(isAvailable),
            issuedTo == null ? "null" : issuedTo
        );
    }
}