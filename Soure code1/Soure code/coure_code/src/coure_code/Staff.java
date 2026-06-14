import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class Staff extends Employee {

    public Staff(int id, String userName, String password, String email, String fullName, String phone, String address,
			String employeeCode, double salary, Date joinDate, String status, String department) {
		super(id, userName, password, email, fullName, phone, address, employeeCode, salary, joinDate, status,
				department);
	}

    // Kiem tra phong con trong theo thoi gian yeu cau
	public List<Room> checkRoomAvailability(Date date) {
		System.out.println("Dang kiem tra cac phong trong vao ngay: " + date);
        return new ArrayList<>(); 
    }

	// Tao booking moi cho khach hang
    public boolean createBooking(Booking data) {
    	System.out.println("Da khoi tao don dat phong moi.");
        return true;
    }

    // Xu ly thu tuc check-in cho khach hang
    public void processCheckIn(int bookingId) {
    	System.out.println("Le tan dang Check-in cho don dat phong ID: " + bookingId);
    }

    // Xu ly check-out va tao hoa don
    public Invoice processCheckOut(int bookingId) {
    	System.out.println("Le tan dang Check-out va xuat hoa don cho don dat phong ID: " + bookingId);
        return null;
    }

    // Dieu chinh thong tin hoa don 
    public void adjustInvoice(int invoiceId, double amount) {
    	System.out.println("Da dieu chinh hoa don " + invoiceId + " voi so tien: " + amount);
    }

	@Override
	public double calculateSalary() {
		// TODO Auto-generated method stub
		return getSalary() * 1.5;
	}
}