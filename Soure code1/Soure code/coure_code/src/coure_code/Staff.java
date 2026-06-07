import java.util.List;
import java.util.Date;

public class Staff extends Employee {

    public Staff(int id, String userName, String password, String email, String fullName, String phone, String address,
			String employeeCode, double salary, Date joinDate, String status, String department) {
		super(id, userName, password, email, fullName, phone, address, employeeCode, salary, joinDate, status,
				department);
	}

	public List<Room> checkRoomAvailability(Date date) {
        return null;
    }

    public boolean createBooking(Booking data) {
        return false;
    }

    public void processCheckIn(int bookingId) {
    }

    public Invoice processCheckOut(int bookingId) {
        return null;
    }

    public void adjustInvoice(int invoiceId, double amount) {
    }
}