public abstract class User {
	private int id;
	private String userName;
	private String password;
	private String email;
	private String fullName;
	private String phone;
	private String address;

	public User(int id, String userName, String password, String email, String fullName, String phone, String address) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.fullName = fullName;
		this.phone = phone;
		this.address = address;
	}
	
	// Xac thuc tai khoan dang nhap he thong
	public boolean login(String username, String password) {
		return this.userName.equals(username) && this.password.equals(password);
	}

	// Dang xuat tai khoan khoi he thong
	public void logout() {
		System.out.println(userName + " Dang Xuat");
	}

	// Thay doi mat khau
	public boolean changePassword(String oldPass, String newPass) {
		if (this.password.equals(oldPass)) {
			this.password = newPass;
			return true;
		}
		return false;
	}

	// Cap nhat thong tin ca nhan
	public void updateProfile(User data) {
		this.fullName = data.getFullName(); 
		this.phone = data.getPhone();
		this.email = data.getEmail();
		this.address = data.getAddress();
	}

	// Kiem tra trang thai hoat dong
	public boolean isActive() {
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}