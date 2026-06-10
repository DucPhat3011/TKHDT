package coure_code;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Admin extends Employee {
    public Admin(int id, String userName, String password, String email, String fullName, String phone, String address, String employeeCode, double salary) {
        super(id, userName, password, email, fullName, phone, address, employeeCode, salary, "Quản trị viên");
    }

    public void manageUsers() {
        System.out.println("Admin đang quản lý người dùng...");
    }

    public void manageRooms() {
        System.out.println("Admin đang thiết lập hệ thống phòng...");
    }

    public void viewReport() {
        System.out.println("Admin đang xem báo cáo doanh thu...");
    }

    @Override
    public double calculateSalary() {
        return getSalary() * 2.0; // Hệ số lương của Admin
    }
}