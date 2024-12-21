
package com.library.dao;

import com.library.model.Book;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    private static final String BOOKS_FILE = "resources/data/books.txt";
    private List<Book> books;

    public BookDao() {
        this.books = loadBooks();
    }

    private List<Book> loadBooks() {
        List<Book> bookList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(BOOKS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Book book = new Book(parts[0], parts[1], parts[2], parts[3]);
                book.setAvailable(Boolean.parseBoolean(parts[4]));
                book.setBorrowerId(parts[5].equals("null") ? null : parts[5]);
                bookList.add(book);
            }
        } catch (IOException e) {
            System.err.println("Error loading books: " + e.getMessage());
        }
        return bookList;
    }

    private void saveBooks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKS_FILE))) {
            for (Book book : books) {
                writer.write(String.format("%s,%s,%s,%s,%b,%s\n",
                        book.getId(), book.getTitle(), book.getAuthor(), book.getIsbn(),
                        book.isAvailable(), book.getBorrowerId() == null ? "null" : book.getBorrowerId()));
            }
        } catch (IOException e) {
            System.err.println("Error saving books: " + e.getMessage());
        }
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    public Book findById(String id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void updateBook(Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(updatedBook.getId())) {
                books.set(i, updatedBook);
                saveBooks();
                break;
            }
        }
    }
}