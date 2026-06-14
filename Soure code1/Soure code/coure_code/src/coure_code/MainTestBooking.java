
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainTestBooking {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BookingManager bookingManager = new BookingManager();
            BookingView bookingView = new BookingView();
            
            BookingController bookingController = new BookingController(bookingManager, bookingView);
            
            // JPanel -> JFrame
            JFrame frame = new JFrame("He Thong Quan Ly Dat Phong Khach San");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(bookingView); 
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}