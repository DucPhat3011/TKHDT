
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainTestPayment {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			RoomManager roomManager = new RoomManager();
			RoomFactory roomFactory = new RoomFactory();
			RoomManagementView roomView = new RoomManagementView();
			RoomController roomController = new RoomController(roomManager, roomFactory, roomView);

			List<Promotion> list = new ArrayList<Promotion>();
			list.add(new Promotion("GIAM10", 10, Date.valueOf("2027-12-31")));
			PromotionEngine promotionEngine = new PromotionEngine(list);
			promotionEngine.setPromotions(list);

			ServiceManagementView serviceView = new ServiceManagementView();
			ServiceManager serviceManager = new ServiceManager(new ArrayList<>(), null);
			ServiceController serviceController = new ServiceController(serviceManager, serviceView);

			PaymentView paymentView = new PaymentView();

			Payment paymentModel = new CashPayment(1, 0.0, new java.sql.Date(System.currentTimeMillis()),
					PaymentMethod.CASH, null, PaymentStatus.PENDING, 5000000, 0.0);

			PaymentController paymentController = new PaymentController(paymentModel, paymentView, roomController,
					promotionEngine, serviceManager, null);

			JFrame mainFrame = new JFrame("He Thong Quan Ly Thanh Toan Khach San");
			mainFrame.setSize(900, 600);
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mainFrame.setLocationRelativeTo(null);

			mainFrame.add(paymentView);
			mainFrame.setVisible(true);
		});
	}
}