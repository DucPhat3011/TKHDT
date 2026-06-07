public class NotificationService implements IObserver {

    @Override
    public void update(String message) {
    }

    public void sendBookingConfirmation(Booking booking, Customer customer) {
    }

    public void sendCheckInNotification(Booking booking, Customer customer) {
    }

    public void sendCheckOutReminder(Booking booking, Customer customer) {
    }

    public void sendPaymentReceipt(Payment payment, Customer customer) {
    }

    public void sendCancellationConfirmation(Booking booking, Customer customer) {
    }
}