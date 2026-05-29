package coure_code;

import java.util.Date;

public class Employee extends User {
    protected String employeeCode;
    protected double salary;
    protected Date joinDate;
    protected String status;
    protected String department;
    
    public String getWorkSchedule() {
        return "Lịch làm việc mặc định: 8h - 17h";
    }

	public void updateRoomStatus(int roomId, RoomStatus status) {
		// TODO Auto-generated method stub
		
	}
}
