
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			// 1. Quan ly phong
			RoomManager roomManager = new RoomManager();
			RoomFactory roomFactory = new RoomFactory();
			RoomManagementView roomView = new RoomManagementView();
			RoomController roomController = new RoomController(roomManager, roomFactory, roomView);
			roomView.setRoomController(roomController);

			// 2. Khuyen mai
			List<Promotion> list = new ArrayList<Promotion>();
			list.add(new Promotion("GIAM10", 10, Date.valueOf("2027-12-31")));
			PromotionEngine promotionEngine = new PromotionEngine(list);
			promotionEngine.setPromotions(list);

			// 3. Dich vu
			ServiceManagementView serviceView = new ServiceManagementView();
			ServiceManager serviceManager = new ServiceManager(new ArrayList<>(), null);
			ServiceController serviceController = new ServiceController(serviceManager, serviceView);
			serviceView.setController(serviceController);

			// 4. Dat phong
			BookingManager bookingManager = new BookingManager();
			BookingView bookingView = new BookingView();
			BookingController bookingController = new BookingController(bookingManager, bookingView);
			bookingView.setBookingController(bookingController);

			// 5. Thanh toan
			PaymentView paymentView = new PaymentView();
			CashPayment cashPayment = new CashPayment(1, 0.0, new Date(System.currentTimeMillis()), PaymentMethod.CASH,
					null, PaymentStatus.PENDING, 5000000, 0.0);
			PaymentController paymentController = new PaymentController(cashPayment, paymentView, roomController,
					promotionEngine, serviceManager, bookingManager);

			// Giao dien Frame chinh
			JFrame mainFrame = new JFrame("He Thong Quan Ly Khach San");
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

			// Tao cac thanh Tab chua cac View
			JTabbedPane tabbedPane = new JTabbedPane();
			tabbedPane.addTab("Quan Ly Phong", roomView);
			tabbedPane.addTab("Dat Phong Khach San", bookingView); 
			tabbedPane.addTab("Quan Ly Dich Vu", serviceView);
			tabbedPane.addTab("Thanh Toan Hoa Don", paymentView);

			mainFrame.add(tabbedPane);
			mainFrame.setVisible(true);
		});
	}
}