package coure_code;

public class User {
    private int id;
    private String userName;
    private String password;
    private String email;
    private String fullName;
    private String phone;
    private String address;

    public boolean login() {
        return true;
    }

    public void logout() {
    	
    }

    public boolean changePassword(String oldPass, String newPass) {
        return true;
    }

    public void updateProfile(User data) {
        this.fullName = data.getFullName();
        this.email = data.getEmail();
        this.phone = data.getPhone();
        this.address = data.getAddress();
    }

    public boolean isActive() {
        return true;
    }

    
    public String getEmail() { 
    	return email; 
    	}

    public String getFullName() {
    	return fullName; 
    	}

    public String getPhone() { 
    	return phone; 
    }

    public String getAddress() { 
    	return address;
    	}
}
