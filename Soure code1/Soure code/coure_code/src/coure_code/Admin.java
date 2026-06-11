package coure_code;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Admin extends Employee {
    public Admin(int id, String userName, String password, String email, String fullName, String phone, String address, String employeeCode, double salary) {
        super(id, userName, password, email, fullName, phone, address, employeeCode, salary, "Quan tri vien");
    }

    public void manageUsers() {
        System.out.println("Admin dang quan ly nguoi dung...");
    }

    public void manageRooms() {
        System.out.println("Admin dang thiet lap he thong phong...");
    }

    public void viewReport() {
        System.out.println("Admin dang xem bao cao doanh thu...");
    }

    @Override
    public double calculateSalary() {
        return getSalary() * 2.0; 
    }
}