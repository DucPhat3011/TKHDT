import java.util.Date;

public class Employee extends User {
	private String employeeCode;
    private double salary;
    private Date joinDate;
    private String status;
    private String department;

    public Employee(int id, String userName, String password, String email, String fullName, String phone,
			String address, String employeeCode, double salary, Date joinDate, String status, String department) {
		super(id, userName, password, email, fullName, phone, address);
		this.employeeCode = employeeCode;
		this.salary = salary;
		this.joinDate = joinDate;
		this.status = status;
		this.department = department;
	}

	public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
    
    public String getWorkSchedule() {
        return "";
    }

}