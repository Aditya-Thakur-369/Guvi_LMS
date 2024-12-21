package com.library.service;

    

import com.library.dao.BookDao;
import com.library.dao.UserDao;
import com.library.model.Book;
import com.library.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class LibraryService {
    private final BookDao bookDao;
    private final UserDao userDao;
    private User currentUser;

    public LibraryService() {
        this.bookDao = new BookDao();
        this.userDao = new UserDao();
    }

    public boolean login(String username, String password) {
        User user = userDao.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            return true;
        }
        return false;
    }

    public List<Book> searchBooks(String searchTerm) {
        return bookDao.getAllBooks().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(searchTerm.toLowerCase()) ||
                        book.getAuthor().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());
    }

    public boolean borrowBook(String bookId, String userId) {
        Book book = bookDao.findById(bookId);
        User user = userDao.findById(userId);
        
        if (book != null && user != null && book.isAvailable()) {
            book.setAvailable(false);
            book.setBorrowerId(userId);
            bookDao.updateBook(book);
            return true;
        }
        return false;
    }

    public boolean returnBook(String bookId, String userId) {
        Book book = bookDao.findById(bookId);
        
        if (book != null && !book.isAvailable() && book.getBorrowerId().equals(userId)) {
            book.setAvailable(true);
            book.setBorrowerId(null);
            bookDao.updateBook(book);
            return true;
        }
        return false;
    }

    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }
}