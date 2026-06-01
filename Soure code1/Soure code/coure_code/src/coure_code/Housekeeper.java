package coure_code;

public class Housekeeper extends Employee {
    public Housekeeper(int id, String username, String password, String fullName, String email, String phone, String address, double baseSalary) {
        super(id, username, password, fullName, email, phone, address, baseSalary);
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() * 1.0; // Hệ số lương của Buồng phòng
    }

    public void updateRoomStatus() {}
}