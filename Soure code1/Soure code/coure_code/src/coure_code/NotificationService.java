
import java.util.List;

public class NotificationService implements IObserver {

    private EmailService emailService;

    public NotificationService() {
        this.emailService = new EmailService();
    }

    // Gửi thông báo xác nhận booking
    public void sendBookingConfirmation(Booking booking, Customer customer) {
        System.out.println("[NotificationService] Gửi xác nhận booking...");
        System.out.println("Khách hàng: " + customer.getName());
        System.out.println("Booking ID: " + booking.getBookingId());
        System.out.println("Trạng thái: " + booking.getStatus());
    }

    // Gửi thông báo check-in
    public void sendCheckInNotification(int bookingId, Customer customer) {
        System.out.println("[NotificationService] Gửi thông báo check-in...");
        System.out.println("Khách hàng: " + customer.getName());
        System.out.println("Booking ID: " + bookingId);
    }

    // Gửi thông báo nhắc nhở check-out
    public void sendCheckOutReminder(int bookingId, Customer customer) {
        System.out.println("[NotificationService] Gửi nhắc nhở check-out...");
        System.out.println("Khách hàng: " + customer.getName());
        System.out.println("Booking ID: " + bookingId);
    }

    // Gửi thông báo thanh toán
    public void sendPaymentRecap(Payment payment, Customer customer) {
        System.out.println("[NotificationService] Gửi nhắc nhở thanh toán...");
        System.out.println("Khách hàng: " + customer.getName());
        System.out.println("Số tiền: " + payment.getAmount());
    }

    // Gửi thông báo xác nhận hủy phòng
    public void sendCancellationConfirmation(Booking booking, Customer customer) {
        System.out.println("[NotificationService] Gửi xác nhận hủy phòng...");
        System.out.println("Khách hàng: " + customer.getName());
        System.out.println("Booking ID: " + booking.getBookingId());
    }

    // Nhận thông báo từ Booking khi trạng thái thay đổi
    @Override
    public void update(String message) {
        System.out.println("[NotificationService] Nhận thông báo: " + message);
    }
}