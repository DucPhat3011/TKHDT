package coure_code;

public class Staff extends Employee {
    public Staff(int id, String username, String password, String fullName, String email, String phone, String address, double baseSalary) {
        super(id, username, password, fullName, email, phone, address, baseSalary);
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() * 1.5; // Hệ số lương của Lễ tân
    }

    public void processBooking() {}
    public void checkIn() {}
    public void checkOut() {}
}