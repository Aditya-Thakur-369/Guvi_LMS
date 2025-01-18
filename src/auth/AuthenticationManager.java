package auth;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import models.User;
import storage.LocalFileHandler;


public class AuthenticationManager {
    private static AuthenticationManager instance;
    private Map<String, User> loggedInUsers;

    private AuthenticationManager() {
        loggedInUsers = new HashMap<>();
    }

    public static AuthenticationManager getInstance() {
        if (instance == null) {
            instance = new AuthenticationManager();
        }
        return instance;
    }

    public boolean login(String id, String password) {
        User user = LocalFileHandler.findUser(id);
        if (user != null && hashPassword(password).equals(user.getPassword())) {
            loggedInUsers.put(id, user);
            return true;
        }
        return false;
    }

    public void logout(String id) {
        loggedInUsers.remove(id);
    }

    public User getCurrentUser(String id) {
        return loggedInUsers.get(id);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            BigInteger number = new BigInteger(1, hash);
            return number.toString(16);
        } catch (NoSuchAlgorithmException e) {
            return password; // Fallback to plain password
        }
    }
}