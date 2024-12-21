package com.library.dao;

import com.library.model.User;
import com.library.model.UserType;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final String USERS_FILE = "resources/data/users.txt";
    private List<User> users;

    public UserDao() {
        this.users = loadUsers();
    }

    private List<User> loadUsers() {
        List<User> userList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                userList.add(new User(parts[0], parts[1], parts[2], UserType.valueOf(parts[3])));
            }
        } catch (IOException e) {
            System.err.println("Error loading users: " + e.getMessage());
        }
        return userList;
    }

    private void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE))) {
            for (User user : users) {
                writer.write(String.format("%s,%s,%s,%s\n",
                        user.getId(), user.getUsername(), user.getPassword(), user.getUserType()));
            }
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }

    public User findById(String id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public User findByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}