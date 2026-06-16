
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportService {
	private int idCounter = 1;
	private ServiceManager serviceManager;
	private BookingManager bookingManager;
	private List<IReport> reportHistory = new ArrayList<>();

	public ReportService(ServiceManager serviceManager, BookingManager bookingManager) {
		this.serviceManager = serviceManager;
		this.bookingManager = bookingManager;
	}

	public Report getReport(String type, Date startDate, Date endDate) {
		int totalBookings = (bookingManager != null) ? bookingManager.getTotalBookings() : Report.getStaticTotalInvoices();
		double totalRevenue = Report.getStaticTotalRevenue();
		int totalCustomers = Report.getStaticTotalCustomers();

		Report report = new Report(idCounter++, "Bao cao " + type, type, startDate, endDate, new Date(), "",
				totalBookings, totalRevenue, totalCustomers);

		reportHistory.add(report);
		return report;
	}

	public CompositeReport getCompositeReport(List<String> types, Date startDate, Date endDate) {
		List<IReport> subReports = new ArrayList<>();
		for (String type : types) {
			subReports.add(getReport(type, startDate, endDate));
		}
		return new CompositeReport("Bao cao Tong Hop", subReports);
	}

	public File exportReport(IReport report, String format) {
		if (report != null) return report.export(format);
		return null;
	}

	public List<IReport> getAllReportsHistory() {
		return reportHistory;
	}
}