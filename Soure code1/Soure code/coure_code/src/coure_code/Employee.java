package coure_code;

import java.util.Date;

public abstract class Employee extends User {
    private String employeeCode;
    private double salary;
    private Date joinDate;
    private String status;
    private String department;

    public Employee(int id, String userName, String password, String email, String fullName, String phone, String address, String employeeCode, double salary, String department) {
        super(id, userName, password, email, fullName, phone, address);
        this.employeeCode = employeeCode;
        this.salary = salary;
        this.joinDate = new Date();
        this.status = "Active";
        this.department = department;
    }

    public String getWorkSchedule() {
        return "Ca làm việc tiêu chuẩn: 8h00 - 17h00";
    }

    // Hàm Đa hình giúp tính lương cho từng vị trí cụ thể
    public abstract double calculateSalary();

    public double getSalary() {
        return salary;
    }
}