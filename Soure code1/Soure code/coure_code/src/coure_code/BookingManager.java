import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class BookingManager {
    private List<Booking> bookings;

    public BookingManager(List<Booking> bookings) {
		this.bookings = new ArrayList<Booking>();
	}

	public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
    
    public Booking createBooking(Customer customer, Room room, Date checkIn, Date checkOut) {
        return null;
    }

    public boolean updateBooking(int bookingId, Object newData) {
        return false;
    }

    public boolean cancelBooking(int bookingId, String reason) {
        return false;
    }

    public void confirmBooking(int bookingId) {
    }

    public Booking getBookingById(int bookingId) {
        return null;
    }

    public List<Booking> getBookingsByCustomer(String customerId) {
        return null;
    }

    public void updateBookingStatus(int bookingId, BookingStatus status) {
    }

}