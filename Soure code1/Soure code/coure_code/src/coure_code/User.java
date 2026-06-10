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
        this.id = id; this.username = username; this.password = password;
        this.fullName = fullName; this.email = email; this.phone = phone; this.address = address;
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
    public void logout() { System.out.println(username + " đã đăng xuất."); }
    public boolean changePassword(String oldPass, String newPass) {
        if (this.password.equals(oldPass)) { this.password = newPass; return true; }
        return false;
    }
    public void updateProfile(User data) {
        this.fullName = data.getFullName(); this.phone = data.getPhone();
    }
    public boolean isActive() { return true; }

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFullName() {
		return fullName;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

  
}