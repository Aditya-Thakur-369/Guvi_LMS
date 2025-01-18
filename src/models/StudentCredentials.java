package models;

public class StudentCredentials {
    private String id;
    private String password;

    public StudentCredentials(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() { return id; }
    public String getPassword() { return password; }
}