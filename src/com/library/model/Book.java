
public class Book {
    // Fields
    private String id;
    private String title;
    private String author;
    private String isbn;
    private boolean available;
    private String borrowerId;

    // Constructor
    public Book(String id, String title, String author, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = true;
        this.borrowerId = null;
    }

    // Getters
    public String getId() { 
        return id; 
    }
    
    public String getTitle() { 
        return title; 
    }
    
    public String getAuthor() { 
        return author; 
    }
    
    public String getIsbn() { 
        return isbn; 
    }
    
    public boolean isAvailable() { 
        return available; 
    }
    
    public String getBorrowerId() { 
        return borrowerId; 
    }

    // Setters
    public void setId(String id) { 
        this.id = id; 
    }
    
    public void setTitle(String title) { 
        this.title = title; 
    }
    
    public void setAuthor(String author) { 
        this.author = author; 
    }
    
    public void setIsbn(String isbn) { 
        this.isbn = isbn; 
    }
    
    public void setAvailable(boolean available) { 
        this.available = available; 
    }
    
    public void setBorrowerId(String borrowerId) { 
        this.borrowerId = borrowerId; 
    }

    // ToString method
    @Override
    public String toString() {
        return "Book[id=" + id + 
               ", title=" + title + 
               ", author=" + author + 
               ", isbn=" + isbn + 
               ", available="+ available + "]";
    }
}