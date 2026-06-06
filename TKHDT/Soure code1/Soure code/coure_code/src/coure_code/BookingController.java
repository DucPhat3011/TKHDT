package coure_code;

import java.time.LocalDate;

// BookingController đóng vai trò Controller trong mô hình MVC
// Nhận yêu cầu từ BookingView, xử lý qua BookingManager
public class BookingController {

    // BookingManager để thao tác dữ liệu booking (theo diagram: bookingManager: BookingManager)
    private BookingManager bookingManager;

    // Constructor nhận BookingManager từ ngoài truyền vào
    public BookingController(BookingManager bookingManager) {
        this.bookingManager = bookingManager;
    }

    public void handleBookingRequest(String customerId, String roomType,
                                     LocalDate checkIn, LocalDate checkOut) {
        Customer customer = new Customer(1, customerId, "", "", "", "", "");

        // Tạo Room đơn giản từ roomType
        Room room = new Room() {
            @Override
            public double calculatePrice() {
                return 0;
            }
        };
        room.setRoomNumber(roomType);

        // Gọi BookingManager để tạo booking mới
        Booking newBooking = bookingManager.createBooking(customer, room, checkIn, checkOut);

        // Đăng ký NotificationService làm observer
        NotificationService notificationService = new NotificationService();
        newBooking.attach(notificationService);

        // Đăng ký EmailService làm observer
        EmailService emailService = new EmailService();
        newBooking.attach(emailService);

        System.out.println("[BookingController] Tạo booking thành công! ID: #" + newBooking.getBookingId());
    }
}