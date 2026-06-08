import javax.swing.SwingUtilities;


public class MainTestBooking {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BookingManager bookingManager = new BookingManager();
            BookingController bookingController = new BookingController(bookingManager);
            BookingView bookingView = new BookingView(bookingController);
            bookingView.render();
        });
    }
}