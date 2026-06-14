import java.util.Date;

public class Admin extends Employee {

	public Admin(int id, String userName, String password, String email, String fullName, String phone, String address,
			String employeeCode, double salary, Date joinDate, String status, String department) {
		super(id, userName, password, email, fullName, phone, address, employeeCode, salary, joinDate, status,
				department);
	}

	// Quan ly tai khoan nguoi dung
	public void manageUsers() {
		System.out.println("Admin dang quan ly nguoi dung...");
	}

	// Quan ly thong tin phong
	public void manageRooms() {
		System.out.println("Admin dang thiet lap he thong phong...");
	}

	// Xem cac bao cao thong ke
	public void viewReport() {
		System.out.println("Admin dang xem bao cao doanh thu...");
	}

	@Override
	public double calculateSalary() {
		// TODO Auto-generated method stub
		return getSalary() * 2.0;
	}

}