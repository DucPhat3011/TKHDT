import java.util.Date;

public class Admin extends Employee {

	public Admin(int id, String userName, String password, String email, String fullName, String phone, String address,
			String employeeCode, double salary, Date joinDate, String status, String department) {
		super(id, userName, password, email, fullName, phone, address, employeeCode, salary, joinDate, status,
				department);
	}

	public void manageUsers() {
	}

	public void manageRooms() {
	}

	public void viewReport() {
	}
}