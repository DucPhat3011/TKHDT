import java.time.LocalDate;


public class BookingController {

    private BookingManager bookingManager;
    private RoomFactory roomFactory; 

    public BookingController(BookingManager bookingManager) {
        this.bookingManager = bookingManager;
        this.roomFactory = new RoomFactory(); // Khởi tạo Factory luôn
    }

    public void handleBookingRequest(String customerId, String roomType, LocalDate checkIn, LocalDate checkOut) {
    	Customer customer = new Customer(1, customerId, "", "", "", "", "", "", "", 0, null);

        Room room = roomFactory.createRoom(roomType);
        if (room == null) {
            System.out.println("[BookingController] Lỗi: Loại phòng không hợp lệ!");
            return;
        }

        Booking newBooking = bookingManager.createBooking(customer, room, checkIn, checkOut);

        NotificationService notificationService = new NotificationService();
        
        System.out.println("[BookingController] Tạo booking thành công! ID: #" + newBooking.getBookingId());
    }
}