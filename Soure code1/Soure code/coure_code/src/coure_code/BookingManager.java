
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingManager {
	private List<Booking> bookings;

	public BookingManager() {
		this.bookings = new ArrayList<>();
	}

	// Tao booking moi trong he thong
	public Booking createBooking(Customer customer, Room room, LocalDate checkIn, LocalDate checkOut) {
		int newId = bookings.size() + 1;
		Booking newBooking = new Booking(newId, customer, 0, null, null);
		Date checkInDate = Date.from(checkIn.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
		Date checkOutDate = Date.from(checkOut.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());

		BookingDetail detail = new BookingDetail();
		detail.setDetailId(newId);
		detail.setRoom(room);
		detail.setCheckInDate(checkInDate);
		detail.setCheckOutDate(checkOutDate);
		detail.setPriceAtBooking(room != null ? 500000 : 0);
		detail.setNumberOfGuests(1);

		newBooking.addBookingDetail(detail);
		bookings.add(newBooking);
		return newBooking;

	}

	// Cap nhat thong tin booking
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

	// Huy booking theo ma
	public boolean cancelBooking(int bookingId) {
		for (Booking booking : bookings) {
			if (booking.getBookingId() == bookingId) {
				if (!booking.isEligibleForCancellation()) {
					return false;
				}
				booking.cancel("Huy boi he thong");
				return true;
			}
		}
		return false;
	}

	// Xac nhan booking
	public boolean confirmBooking(int bookingId) {
		for (Booking booking : bookings) {
			if (booking.getBookingId() == bookingId) {
				booking.updateBookingStatus(BookingStatus.CONFIRMED);
				return true;
			}
		}
		return false;
	}

	// Lay booking theo ID
	public Booking getBookingById(int bookingId) {
		for (Booking booking : bookings) {
			if (booking.getBookingId() == bookingId) {
				return booking;
			}
		}
		return null;
	}

	// Lay danh sach booking theo khach hang
	public List<Booking> getBookingsByCustomer(int customerId) {
		List<Booking> result = new ArrayList<>();
		for (Booking booking : bookings) {
			if (booking.getCustomer() != null && booking.getCustomer().getId() == customerId) {
				result.add(booking);
			}
		}
		return result;
	}

	// Cap nhat trang thai booking
	public void updateBookingStatus(int bookingId, BookingStatus status) {
		for (Booking booking : bookings) {
			if (booking.getBookingId() == bookingId) {
				booking.updateBookingStatus(status);
				return;
			}
		}
	}

}