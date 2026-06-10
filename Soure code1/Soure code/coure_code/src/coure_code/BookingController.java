import java.time.LocalDate;

public class BookingController {

	private BookingManager bookingManager;
	private RoomFactory roomFactory;

	public BookingController(BookingManager bookingManager) {
		this.bookingManager = bookingManager;
		this.roomFactory = new RoomFactory();
	}

	public void handleBookingRequest(String customerId, String roomType, LocalDate checkIn, LocalDate checkOut) {
		Customer customer = new Customer(1, customerId, "", "", "", "", "", "", "", 0, null);
		Room room = roomFactory.createRoom(roomType);
		if (room == null) {
			System.out.println("Loi: Loai phong khong hop le!");
			return;
		}
		Booking newBooking = bookingManager.createBooking(customer, room, checkIn, checkOut);
		NotificationService notificationService = new NotificationService();
		newBooking.attach(notificationService);
		System.out.println("Tao booking thanh cong! ID: #" + newBooking.getBookingId());
	}
}