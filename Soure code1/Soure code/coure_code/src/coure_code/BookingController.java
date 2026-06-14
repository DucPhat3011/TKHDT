
import java.time.LocalDate;

public class BookingController {
	private BookingManager modelManager;
	private BookingView view;

	public BookingController(BookingManager modelManager, BookingView view) {
		this.modelManager = modelManager;
		this.view = view;

		this.view.setBookingController(this);
	}

	// Xu ly yeu cau dat phong
	public Booking handleBookingRequest(String customerId, String roomNumber, String roomType, LocalDate checkIn,
			LocalDate checkOut) {
		try {
			Customer customer = new Customer(0, roomType, roomType, roomType, roomType, roomType, roomType, roomType,
					roomType, 0, null);
			customer.setId(Integer.parseInt(customerId));
			customer.setFullName("Khách Hàng " + customerId);

			RoomFactory roomFactory = new RoomFactory();
			Room selectedRoom = roomFactory.createRoom(roomType);
			if (selectedRoom != null) {
				selectedRoom.setRoomNumber(roomNumber);
			}

			return modelManager.createBooking(customer, selectedRoom, checkIn, checkOut);

		} catch (NumberFormatException e) {
			System.out.println("Lỗi: Mã khách hàng phải là ký tự số nguyên!");
			throw e;
		} catch (Exception e) {
			System.out.println("Lỗi phát sinh trong quá trình xử lý nghiệp vụ đặt phòng: " + e.getMessage());
			throw e;
		}
	}
}