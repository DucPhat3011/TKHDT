package coure_code;

public class Admin extends Employee {
    public Admin(int id, String username, String password, String fullName, String email, String phone, String address, double baseSalary) {
        super(id, username, password, fullName, email, phone, address, baseSalary);
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() * 2.0; // Hệ số lương của Admin
    }

    public void manageUsers() {}
    public void manageRooms() {}
    public void viewReport() {}
}