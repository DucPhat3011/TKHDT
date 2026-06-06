package coure_code;

public abstract class Employee extends User {
    private double salary;

    public Employee(int id, String username, String password, String fullName, String email, String phone, String address, double baseSalary) {
        super(id, username, password, fullName, email, phone, address);
        this.salary = baseSalary;
    }

    public double getBaseSalary() {
        return salary;
    }

    public abstract double calculateSalary();
}