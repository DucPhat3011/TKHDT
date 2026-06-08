
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingManager {
    private List<Booking> bookings;

    public BookingManager() {
        this.bookings = new ArrayList<>();
    }

  
	public Booking createBooking(Customer customer, Room room, LocalDate checkIn, LocalDate checkOut) {
        int newId = bookings.size() + 1;
        java.time.LocalDateTime checkInDateTime = checkIn.atStartOfDay();
        java.time.LocalDateTime checkOutDateTime = checkOut.atStartOfDay();
        Booking newBooking = new Booking(newId, customer, checkInDateTime, checkOutDateTime, null, null);
        bookings.add(newBooking);
        return newBooking;

    }

    public boolean updateBooking(int bookingId, Booking newData) {
        for (int i = 0; i < bookings.size(); i++) {
            Booking booking = bookings.get(i);
            if (booking.getBookingId() == bookingId) {
                bookings.set(i, newData);
                return true;
            }
        }
        return false;
    }

    public boolean cancelBooking(int bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId() == bookingId) {
                if (!booking.isEligibleForCancellation()) {
                    return false;
                }
                booking.cancel("Hủy bởi hệ thống");
                return true;
            }
        }
        return false;
    }

    // Xác nhận booking
    public boolean confirmBooking(int bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId() == bookingId) {
                booking.updateBookingStatus(BookingStatus.CONFIRMED);
                return true;
            }
        }
        return false;
    }

    // Lấy booking theo ID
    public Booking getBookingById(int bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId() == bookingId) {
                return booking;
            }
        }
        return null;
    }

    // Lấy danh sách booking theo khách hàng
    public List<Booking> getBookingsByCustomer(int customerId) {
        List<Booking> result = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getCustomer() != null) {
                result.add(booking);
            }
        }
        return result;
    }

    // Cập nhật trạng thái booking
    public void updateBookingStatus(int bookingId, BookingStatus status) {
        for (Booking booking : bookings) {
            if (booking.getBookingId() == bookingId) {
                booking.updateBookingStatus(status); // Gọi method của Booking
                return;
            }
        }
    }

    public void attach(NotificationService notificationService) {

    }
}


