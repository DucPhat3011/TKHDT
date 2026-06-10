package coure_code;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Staff extends Employee {
    public Staff(int id, String userName, String password, String email, String fullName, String phone, String address, String employeeCode, double salary) {
        super(id, userName, password, email, fullName, phone, address, employeeCode, salary, "Lễ tân");
    }

    public List<Room> checkRoomAvailability(Date date) {
        System.out.println("Đang kiểm tra các phòng trống vào ngày: " + date);
        return new ArrayList<>(); 
    }

    public boolean createBooking(Booking data) {
        System.out.println("Đã khởi tạo đơn đặt phòng mới.");
        return true;
    }

    public void processCheckIn(int bookingId) {
        System.out.println("Lễ tân đang Check-in cho đơn đặt phòng ID: " + bookingId);
    }

    public Invoice processCheckOut(int bookingId) {
        System.out.println("Lễ tân đang Check-out và xuất hóa đơn cho đơn đặt phòng ID: " + bookingId);
        return null;
    }

    public void adjustInvoice(int invoiceId, double amount) {
        System.out.println("Đã điều chỉnh hóa đơn " + invoiceId + " với số tiền: " + amount);
    }

    @Override
    public double calculateSalary() {
        return getSalary() * 1.5; 
    }
}