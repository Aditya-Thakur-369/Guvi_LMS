

package com.library.util;

public class InputValidator {
    public static boolean isValidIsbn(String isbn) {
        return isbn != null && isbn.matches("\\d{10}|\\d{13}");
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }
}