package coure_code;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Staff extends Employee {
    public Staff(int id, String userName, String password, String email, String fullName, String phone, String address, String employeeCode, double salary) {
        super(id, userName, password, email, fullName, phone, address, employeeCode, salary, "Le tan");
    }

    public List<Room> checkRoomAvailability(Date date) {
        System.out.println("Dang kiem tra cac phong trong vao ngay: " + date);
        return new ArrayList<>(); 
    }

    public boolean createBooking(Booking data) {
        System.out.println("Da khoi tao don dat phong moi.");
        return true;
    }

    public void processCheckIn(int bookingId) {
        System.out.println("Le tan dang Check-in cho don dat phong ID: " + bookingId);
    }

    public Invoice processCheckOut(int bookingId) {
        System.out.println("Le tan dang Check-out va xuat hoa don cho don dat phong ID: " + bookingId);
        return null;
    }

    public void adjustInvoice(int invoiceId, double amount) {
        System.out.println("Da dieu chinh hoa don " + invoiceId + " voi so tien: " + amount);
    }

    @Override
    public double calculateSalary() {
        return getSalary() * 1.5; 
    }
}