import java.util.Date;

public class BookingController {
    private BookingManager bookingManager;

    public BookingController(BookingManager bookingManager) {
		this.bookingManager = bookingManager;
	}

	public void handleBookingRequest(String customerId, String roomType, Date checkIn, Date checkOut) {
    }

    public BookingManager getBookingManager() {
        return bookingManager;
    }

    public void setBookingManager(BookingManager bookingManager) {
        this.bookingManager = bookingManager;
    }
}