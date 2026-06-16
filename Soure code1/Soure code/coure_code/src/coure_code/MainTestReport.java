import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainTestReport {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			ServiceManager serviceManager = new ServiceManager(new java.util.ArrayList<>(), null);
			BookingManager bookingManager = new BookingManager();
			
			ReportService reportService = new ReportService(serviceManager, bookingManager);
			
			ReportView reportView = new ReportView();
			
			ReportController reportController = new ReportController(reportService, reportView);
			
			JFrame mainFrame = new JFrame("Hệ Thống Quản Lý Báo Cáo");
			mainFrame.setSize(900, 600);
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mainFrame.setLocationRelativeTo(null);

			mainFrame.add(reportView);
			mainFrame.setVisible(true);
		});
	}
}