
public class NotificationService implements IObserver {

	private EmailService emailService;

	public NotificationService() {
		this.emailService = new EmailService();
	}

	// Gui thong bao xac nhan booking
	public void sendBookingConfirmation(Booking booking, Customer customer) {
		System.out.println("Gui xac nhan booking...");
		System.out.println("Khach hang: " + customer.getName());
		System.out.println("Booking ID: " + booking.getBookingId());
		System.out.println("Trang thai: " + booking.getStatus());
	}

	// Gui thong bao check-in
	public void sendCheckInNotification(int bookingId, Customer customer) {
		System.out.println("Gui thong bao check-in...");
		System.out.println("Khach hang: " + customer.getName());
		System.out.println("Booking ID: " + bookingId);
	}

	// Gui thong bao nhac nho check-out
	public void sendCheckOutReminder(int bookingId, Customer customer) {
		System.out.println("Gui nhac nho check-out...");
		System.out.println("Khach hang: " + customer.getName());
		System.out.println("Booking ID: " + bookingId);
	}

	// Gui thong bao thanh toan
	public void sendPaymentRecap(Payment payment, Customer customer) {
		System.out.println("Gui nhac nho thanh toan...");
		System.out.println("Khach hang: " + customer.getName());
		System.out.println("So tien: " + payment.getAmount());
	}

	// Gui thong bao xac nhan huy phong
	public void sendCancellationConfirmation(Booking booking, Customer customer) {
		System.out.println("Gui xac nhan huy phong...");
		System.out.println("Khach hang: " + customer.getName());
		System.out.println("Booking ID: " + booking.getBookingId());
	}

	// Nhan thong bao tu booking khi trang thai thay doi
	@Override
	public void update(String message) {
		System.out.println("Nhan thong bao: " + message);
	}
}