package coure_code;

public abstract class User {
    private int id;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private String address;

    public User(int id, String username, String password, String fullName, String email, String phone, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void logout() {
        System.out.println("User " + username + " logged out.");
    }

    // Getters and Setters omitted for brevity

    public String getFullName() {
        return fullName;
    }
}