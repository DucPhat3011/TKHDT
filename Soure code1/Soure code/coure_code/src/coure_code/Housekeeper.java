import java.util.Date;

public class Housekeeper extends Employee {
	private String shift;

	public Housekeeper(int id, String userName, String password, String email, String fullName, String phone,
			String address, String employeeCode, double salary, Date joinDate, String status, String department,
			String shift) {
		super(id, userName, password, email, fullName, phone, address, employeeCode, salary, joinDate, status,
				department);
		this.shift = shift;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	// Cap nhat trang thai phong
	public void updateRoomStatus(int roomId, RoomStatus status) {
		System.out.println("Nhan vien don phong  " + roomId + " sang trang thai: " + status);
	}

	@Override
	public double calculateSalary() {
		// TODO Auto-generated method stub
		return getSalary() * 1.0;
	}

}