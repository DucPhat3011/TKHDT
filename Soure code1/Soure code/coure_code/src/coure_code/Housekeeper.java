package coure_code;
public class Housekeeper extends Employee {
    private String shift;

    public Housekeeper(int id, String userName, String password, String email, String fullName, String phone, String address, String employeeCode, double salary, String shift) {
        super(id, userName, password, email, fullName, phone, address, employeeCode, salary, "Buồng phòng");
        this.shift = shift;
    }

    public void updateRoomStatus(int roomId, RoomStatus status) {
        System.out.println("Nhan vien don phong  " + roomId + " sang trang thai: " + status);
    }

    @Override
    public double calculateSalary() {
        return getSalary() * 1.0; 
    }
}